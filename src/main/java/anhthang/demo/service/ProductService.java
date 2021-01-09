package anhthang.demo.service;

import anhthang.demo.dto.ProductDTO;
import anhthang.demo.model.Product;
import anhthang.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public List<ProductDTO> getAllProducts(){
        try {
            return productRepository.getAllProducts();
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Product getProductById(String productID){
        try {
            return productRepository.getProductById(productID);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean createProduct(String productID,String display,String priceIn,int priceOut,int priceSale,int amount,int shipday,String description,String images,int deleted){
        try {
            productRepository.createProduct(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }



}
