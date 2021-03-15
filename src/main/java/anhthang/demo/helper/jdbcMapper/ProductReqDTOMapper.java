package anhthang.demo.helper.jdbcMapper;

import anhthang.demo.dto.ProductReqDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductReqDTOMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductReqDTO productReqDTO = new ProductReqDTO();
        productReqDTO.setProductID(resultSet.getString("productID"));
        productReqDTO.setAmount(resultSet.getInt("amount"));
        return productReqDTO;
    }
}
