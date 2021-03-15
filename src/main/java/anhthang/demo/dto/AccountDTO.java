package anhthang.demo.dto;

import anhthang.demo.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO extends Account {
    String accountID;
    String email;
    String display;
    String password;
    String role;
    String avatar;


}
