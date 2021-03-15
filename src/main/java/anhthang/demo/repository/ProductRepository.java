package anhthang.demo.repository;

import anhthang.demo.dto.ProductDTO;
import anhthang.demo.dto.ProductReqDTO;
import anhthang.demo.helper.jdbcMapper.ProductDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ProductDTO> getAllProducts(Integer sortType, String sortColumn){
        // 0: ASC, 1: DESC, default ASC
        String sortStatus = sortType !=null && sortType == 0
                ? " ASC"
                : " DESC";
//        if (sortType != null) {
//            if (sortType != 0) {
//                sortStatus = " DESC";
//            }
//        }
        String sql = "select * from product where deleted = 0";
        if (sortColumn != null) {
            sql += " order by " + sortColumn + sortStatus;
        }
        System.out.printf("sql:" + sql);
        List<ProductDTO> listProductDTO = jdbcTemplate.query(sql, new ProductDTOMapper());
        return listProductDTO;
    }

//    public List<ProductDTO> getAllProducts(){
//        String sql = "select * from product;";
//        List<ProductDTO> listProductDTO = jdbcTemplate.query(sql, new ProductMapper());
//        return listProductDTO;
//    }

    public ProductDTO getProductById(String productID){
        String sql = "select * from product where productID = ? and deleted = 0; ";
        Object[] params = {productID};
        ProductDTO productDTO = (ProductDTO)jdbcTemplate.queryForObject(sql, new ProductDTOMapper(), params);
        return productDTO;
    }

    public void createProduct(String display,String priceIn,int priceOut,int priceSale,int amount,int shipday,String description,String images,int deleted){
        String sql = "INSERT INTO product(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted) VALUES(?,?,?,?,?,?,?,?,?,?);";
        String uuid = UUID.randomUUID().toString();
        Object[] params = {uuid, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted};
        jdbcTemplate.update(sql, params);
    }

    public ProductDTO getProductByName(String productName){
        String sql = "select * from product where lower(display) = lower(?) and deleted = 0;";
        Object[] params = {productName};
        ProductDTO productDTO = (ProductDTO) jdbcTemplate.queryForObject(sql, new ProductDTOMapper(), params);
        return productDTO;
    }

    public Integer updateProduct(String productID, String display, int priceIn, int priceOut, int priceSale, int amount, int shipday, String description, String images, int deleted){
        String sql = "update product set display = ?, priceIn = ?, priceOut = ?, priceSale = ?, amount = ?, shipday = ?, description = ?, images = ?, deleted = ? where productID = ?;";
        Object[] params = {display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted, productID};
        return jdbcTemplate.update(sql, params);
    }

    public Integer deleteProductByID(String productID){
        String sql = "update product set deleted = 1 where productID = ?;";
        Object[] params = {productID};
        return jdbcTemplate.update(sql, params);
    }

    public List<ProductReqDTO> createReceipt(){


        return null;
    }

    public void checkAmountOfProduct(String productID, int amount){
        ProductDTO productDTO = this.getProductById(productID);
        Integer amountProduct = productDTO.getAmount() - amount;
        if (amountProduct < 0){
            System.out.println("khong du san pham"); //lam throw tai day
        }else {
            productDTO.setAmount(amountProduct);
            this.updateProduct(productDTO.getProductID(),productDTO.getDisplay(), productDTO.getPriceIn(), productDTO.getPriceOut(),productDTO.getPriceSale(),productDTO.getAmount(),productDTO.getShipday(),productDTO.getDescription(),productDTO.getImages(),productDTO.getDeleted());

        }
    }


    // lesson07 dung limit va offset trong sql
//    public List<ProductDTO> getProduct2(Integer limit, Integer offset){
//        String sql = "select * from product limit ?,?;";
//        Object[] params = {offset,limit};
//        List<ProductDTO> list = jdbcTemplate.query(sql, new ProductMapper(), params);
//        return list;
//    }
//
//    public Integer countProduct(){
//        String sql = "select count(productID) from product;";
//        Integer count = jdbcTemplate.update(sql);
//        return count;
//    }

}
