package anhthang.demo.repository;

import anhthang.demo.dto.ProductDTO;
import anhthang.demo.model.Product;
import anhthang.demo.helper.jdbcMapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ProductDTO> getAllProducts(){
        String sql = "select * from product;";
        List<ProductDTO> listProductDTO = jdbcTemplate.query(sql, new ProductMapper());
        return listProductDTO;

    }

    public Product getProductById(String productID){
        String sql = "select * from product where productID = ?; ";
        Object[] params = {productID};
        Product product = (Product)jdbcTemplate.queryForObject(sql, new ProductMapper(), params);
        return product;
    }

    public void createProduct(String productID,String display,String priceIn,int priceOut,int priceSale,int amount,int shipday,String description,String images,int deleted){
        String sql = "INSERT INTO product(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted) VALUES(?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(sql, productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted );
    }



}
