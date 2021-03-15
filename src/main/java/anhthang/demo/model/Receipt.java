package anhthang.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    String receiptID;
    String accountID;
    Integer total_money;
    String status;
    int deleted;
    String create_at;
    String update_at;



}
