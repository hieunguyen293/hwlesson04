package anhthang.demo.dto;

import anhthang.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO extends Product {
    String productID;
    String display;
    int priceSale;
    String images;



}
