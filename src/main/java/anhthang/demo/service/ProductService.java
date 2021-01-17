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


    public List<ProductDTO> getAllProducts(String sortType,String sortColumn){
        try {
            return productRepository.getAllProducts(sortType,sortColumn);
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

    public boolean createProduct(String display,String priceIn,int priceOut,int priceSale,int amount,int shipday,String description,String images,int deleted){
        try {
            productRepository.createProduct(display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public Product getProductByName(String productName){
        try {
            return productRepository.getProductByName(productName);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean updateProductByID(String productID, String display,String priceIn,int priceOut,int priceSale,int amount,int shipday,String description,String images,int deleted){
        try {
            if (productRepository.updateProductByID(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted) != 0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e){

        }
        return false;
    }

    public boolean deleteProductByID(String productID){
        try {
            if (productRepository.deleteProductByID(productID) != 0 ){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){

        }
        return  false;
    }


}
