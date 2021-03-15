package anhthang.demo.repository;

import anhthang.demo.dto.SessionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SessionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer addSession(SessionDTO sessionDTO){
        String sql = "insert into session(userID, token) values(?,?);";
        Object[] params = {sessionDTO.getUserID(), sessionDTO.getToken()};
        return jdbcTemplate.update(sql, params);
    }





}
