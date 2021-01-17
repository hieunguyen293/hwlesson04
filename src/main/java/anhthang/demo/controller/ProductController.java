package anhthang.demo.controller;

import anhthang.demo.dto.ProductDTO;
import anhthang.demo.model.Product;
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


    @GetMapping("/get-all-products")
    public List<ProductDTO> getAllProducts(@RequestParam(required = true) int sortType,@RequestParam(required = true) Integer sortColumn){
        if (sortColumn == 0) {
            if (sortType == 0) {
                return productService.getAllProducts("ASC", "priceOut");
            } else if (sortType == 1) {
                return productService.getAllProducts("DESC", "priceOut");
            } else {
                return productService.getAllProducts("ASC", "priceOut");
            }
        }else if (sortColumn == 1){
            if (sortType == 0) {
                return productService.getAllProducts("ASC", "display");
            } else if (sortType == 1) {
                return productService.getAllProducts("DESC", "display");
            } else {
                return productService.getAllProducts("ASC", "display");
            }
        }else {
            if (sortType == 0) {
                return productService.getAllProducts("ASC", "priceOut");
            } else if (sortType == 1) {
                return productService.getAllProducts("DESC", "priceOut");
            } else {
                return productService.getAllProducts("ASC", "priceOut");
            }
        }

        /*
        String type;
        String column;
        switch(type){
            case 0:
                type = "ASC";
            break;
            case 1:
                type = "DESC";
            break;
            default:
                type = "ASC";
            break;
        switch(column){
            case 0:
                column = "priceOut";
            break;
            case 1:
                column = "display";
            break;
            default:
                column = "priceOut";
            break;
            return
         */



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
    public Product getProductByName(@RequestParam String productName){
        return  productService.getProductByName(productName);
    }

    @PutMapping("/update-product-by-id")
    public Boolean updateProductByID(@RequestParam String productID, @RequestParam String display,@RequestParam String priceIn,@RequestParam int priceOut,@RequestParam int priceSale,@RequestParam int amount,@RequestParam int shipday,@RequestParam String description,@RequestParam String images,@RequestParam int deleted){
        return productService.updateProductByID(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted);
    }

    @DeleteMapping("/delete-product-by-id")
    public Boolean deleteProductByID(@RequestParam String productID){
        return productService.deleteProductByID(productID);
    }


}
