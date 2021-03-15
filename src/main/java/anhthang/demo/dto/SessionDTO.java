package anhthang.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    String userID;
    String token;
    Integer active;

    public SessionDTO(String userID,String token){
        this.userID = userID;
        this.token = token;
    }
}
