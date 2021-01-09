package anhthang.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    String productID;
    String display;
    int priceIn;
    int priceOut;
    int priceSale;
    int amount;
    int shipday;
    String description;
    String images;
    int deleted;
    String create_at;
    String update_at;



}
