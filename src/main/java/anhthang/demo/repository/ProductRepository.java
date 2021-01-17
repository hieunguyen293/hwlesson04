package anhthang.demo.repository;

import anhthang.demo.dto.ProductDTO;
import anhthang.demo.model.Product;
import anhthang.demo.helper.jdbcMapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ProductDTO> getAllProducts(String sortType, String sortColum){
        String sql = "select * from product where deleted = 0 ORDER BY "+sortColum+" "+sortType+";"; // asc || desc
        List<ProductDTO> listProductDTO = jdbcTemplate.query(sql, new ProductMapper());
        return listProductDTO;
    }

//    public List<ProductDTO> getAllProducts(){
//        String sql = "select * from product;";
//        List<ProductDTO> listProductDTO = jdbcTemplate.query(sql, new ProductMapper());
//        return listProductDTO;
//    }

    public Product getProductById(String productID){
        String sql = "select * from product where productID = ? and deleted = 0; ";
        Object[] params = {productID};
        Product product = (Product)jdbcTemplate.queryForObject(sql, new ProductMapper(), params);
        return product;
    }

    public void createProduct(String display,String priceIn,int priceOut,int priceSale,int amount,int shipday,String description,String images,int deleted){
        String sql = "INSERT INTO product(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted) VALUES(?,?,?,?,?,?,?,?,?,?);";
        String uuid = UUID.randomUUID().toString();
        Object[] params = {uuid, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted};
        jdbcTemplate.update(sql, params);
    }

    public Product getProductByName(String productName){
        String sql = "select * from product where lower(display) = lower(?) and deleted = 0;";
        Object[] params = {productName};
        Product product = (Product) jdbcTemplate.queryForObject(sql, new ProductMapper(), params);
        return product;
    }

    public Integer updateProductByID(String productID, String display,String priceIn,int priceOut,int priceSale,int amount,int shipday,String description,String images,int deleted){
        String sql = "update product set display = ?, priceIn = ?, priceOut = ?, priceSale = ?, amount = ?, shipday = ?, description = ?, images = ?, deleted = ? where productID = ?;";
        Object[] params = {display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted, productID};
        return jdbcTemplate.update(sql, params);
    }

    public Integer deleteProductByID(String productID){
        String sql = "update product set deleted = 1 where productID = ?;";
        Object[] params = {productID};
        return jdbcTemplate.update(sql, params);
    }

}
