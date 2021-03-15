package anhthang.demo.repository;

import anhthang.demo.dto.SessionDTO;
import anhthang.demo.helper.jdbcMapper.SessionDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer checkSessionByToken(String token){
        String sql = "select * from session where token = ?;";
        Object[] params = {token};
        return jdbcTemplate.update(sql, params);
    }

    public SessionDTO getSessionByToken(String token){
        String sql = "select * from session where token = ?;";
        Object[] params = {token};
        return (SessionDTO) jdbcTemplate.queryForObject(sql, new SessionDTOMapper(), params);
    }

    public Integer logout(String token){
        String sql = "update session set active = 0 where token = ?;";
        Object[] param = {token};
        return jdbcTemplate.update(sql,param);
    }



}
