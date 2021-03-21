package anhthang.demo.service;

import anhthang.demo.constant.JwtConstant;
import anhthang.demo.dto.AccountDTO;
import anhthang.demo.dto.GetAllProductWithAccInfo.AccountNullPassword;
import anhthang.demo.dto.GetAllProductWithAccInfo.ProductWithAccountInfo;
import anhthang.demo.dto.ProductDTO;
import anhthang.demo.dto.SessionDTO;
import anhthang.demo.helper.jdbcMapper.Jwt;
import anhthang.demo.repository.AccountRepository;
import anhthang.demo.repository.AuthRepository;
import anhthang.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    AccountRepository accountRepository;


    public ProductWithAccountInfo getAllProducts(Integer sortType,String sortColumn, String token){
        Jwt jwt = new Jwt();
        String accountID = jwt.verifyToken(token, JwtConstant.SECRET_KEY);

        if (accountRepository.checkAccoutByAccountID(accountID) != 0){
            AccountDTO accountDTO = accountRepository.getAccountByAccountID(accountID);

            AccountNullPassword accountNullPassword = new AccountNullPassword();
            accountNullPassword.setAccountID(accountDTO.getAccountID());
            accountNullPassword.setEmail(accountDTO.getEmail());
            accountNullPassword.setDisplay(accountDTO.getDisplay());
            accountNullPassword.setRole(accountDTO.getRole());
            accountNullPassword.setAvatar(accountDTO.getAvatar());

            List<ProductDTO> productDTOList = productRepository.getAllProducts(sortType,sortColumn);

            ProductWithAccountInfo productWithAccountInfo = new ProductWithAccountInfo(accountNullPassword,productDTOList);
            return productWithAccountInfo;

        }else{
            System.out.println("khong co user nao tuong ung voi token");
            return null;
        }
    }

    public ProductDTO getProductById(String productID){
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

    public ProductDTO getProductByName(String productName){
        // check name trong nay, neu null thi goi den get all
        try {
            return productRepository.getProductByName(productName);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean updateProductByID(String productID, String display,int priceIn,int priceOut,int priceSale,int amount,int shipday,String description,String images,int deleted){
        try {
            if (productRepository.updateProduct(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted) != 0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e){
            System.out.println(e);
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
            System.out.println(e);
        }
        return  false;
    }

    // lesson07
//    public List<ProductDTO> getProduct2(Integer page, Integer size) {
//        Integer limit = size;
//        Integer offset = (page - 1) * size;
//
//        List<ProductDTO> list = productRepository.getProduct2(limit,offset);
//
//    }





}
