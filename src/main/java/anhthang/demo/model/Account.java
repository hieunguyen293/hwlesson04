package anhthang.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    String accountID;
    String email;
    String display;
    String password;
    enum role {STAFF, ADMIN, USER};
    String avatar;
    int deleted;
    String create_at;
    String update_at;

}
