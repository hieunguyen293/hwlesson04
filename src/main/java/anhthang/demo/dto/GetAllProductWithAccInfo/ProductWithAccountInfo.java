package anhthang.demo.dto.GetAllProductWithAccInfo;

import anhthang.demo.dto.GetAllProductWithAccInfo.AccountNullPassword;
import anhthang.demo.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithAccountInfo {
    AccountNullPassword accountInfo;
    List<ProductDTO> productDTOList;
}
