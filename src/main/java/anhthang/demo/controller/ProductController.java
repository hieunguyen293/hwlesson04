package anhthang.demo.controller;

import anhthang.demo.dto.AccountDTO;
import anhthang.demo.dto.GetAllProductWithAccInfo.ProductWithAccountInfo;
import anhthang.demo.dto.ProductDTO;
import anhthang.demo.model.Product;
import anhthang.demo.service.AccountService;
import anhthang.demo.service.AuthService;
import anhthang.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
//    private final int ASC = 0;
//    private final int DESC = 1;
    @Autowired
    AuthService authService;

    @Autowired
    AccountService accountService;

    @GetMapping("/get-all-products")
    public ProductWithAccountInfo getAllProducts(@RequestParam(required = true) Integer sortType, @RequestParam(required = true) String sortColumn, @RequestHeader String token){
        // query check database tuong ung voi token
        // neu khong co user => fail
        // neu co => lay ra userID
        // lay user infor
        // lay product infor
        if (authService.checkSessionByToken(token) != 0){
            AccountDTO accountDTO = accountService.getAccountByAccountID(authService.getSessionByToken(token).getUserID());
            
        }

        return productService.getAllProducts(sortType, sortColumn, token);

    }

    @GetMapping("/get-product-by-id")
    public Product getProductById(@RequestParam String productID){
        return productService.getProductById(productID);
//        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productDTO,1,"okokokokokok")); //them vao cho biet thay doi status
    }

    @PostMapping("/create-product")
    // Co the dung @RequestBody cho code ngan hon
    public boolean createProduct(@RequestParam String display,@RequestParam String priceIn,@RequestParam int priceOut,@RequestParam int priceSale,@RequestParam int amount,@RequestParam int shipday,@RequestParam String description,@RequestParam String images,@RequestParam int deleted){
        return productService.createProduct(display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted);
    }


//    @GetMapping("/test-ex")
//    public Integer testException(@RequestParam Integer input){
//        try {
//            return input / 0;
//        }catch (Exception e){
//            return -1;
//        }
//    }

    @GetMapping("/get-product-by-name")
    // khong truyen gi => get all
    //
    public Product getProductByName(@RequestParam String productName){
        return  productService.getProductByName(productName);
    }

    @PutMapping("/update-product-by-id")
    // path: PUT product/:id (body)
    // neu khong ton tai thi bao loi: khong ton tai
    // them truong nao thi update khong thi phai giu nguyen
    public Boolean updateProductByID(@RequestParam String productID, @RequestParam String display,@RequestParam int priceIn,@RequestParam int priceOut,@RequestParam int priceSale,@RequestParam int amount,@RequestParam int shipday,@RequestParam String description,@RequestParam String images,@RequestParam int deleted){
        return productService.updateProductByID(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted);
    }

    @DeleteMapping("/delete-product-by-id")
    /*
    path: DELETE product/:id
    khong ton tai => bao la khong ton tai san pham nay
     */
    public Boolean deleteProductByID(@RequestParam String productID){
        return productService.deleteProductByID(productID);
    }

    //truyen vao display = "product1"

    // update product set display = ..., where id = abc
    // tao sql = update product set id = id;
    // conditionSql = 'where id = abc'
    //if(productDto.display != null){
    // Sql +=',dsiplay =' + productDto.display
    //}
    // finalSql = sql + conditionSql




}
