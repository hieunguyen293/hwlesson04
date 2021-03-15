package anhthang.demo.dto;


import anhthang.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetAll extends Product {
    private String productID;
    private String display;
    private int priceSale;
    private String images;
}
