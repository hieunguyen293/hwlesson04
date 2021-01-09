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

    @GetMapping("/get-all-products")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get-product-by-id")
    public Product getProductById(@RequestParam String productID){
        return  (Product) productService.getProductById(productID);
//        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productDTO,1,"okokokokokok")); //them vao cho biet thay doi status
    }

    @PostMapping("/create-product")
    // Co the dung @RequestBody cho code ngan hon
    public boolean createProduct(@RequestParam String productID, @RequestParam String display,@RequestParam String priceIn,@RequestParam int priceOut,@RequestParam int priceSale,@RequestParam int amount,@RequestParam int shipday,@RequestParam String description,@RequestParam String images,@RequestParam int deleted){
        return productService.createProduct(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images, deleted);
    }

}
