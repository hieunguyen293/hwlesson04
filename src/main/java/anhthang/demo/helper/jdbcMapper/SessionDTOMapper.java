package anhthang.demo.helper.jdbcMapper;

import anhthang.demo.dto.SessionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDTOMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setUserID(resultSet.getString("userID"));
        sessionDTO.setToken(resultSet.getString("key"));
        sessionDTO.setActive(resultSet.getInt("active"));
        return sessionDTO;
    }
}
