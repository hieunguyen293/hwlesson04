package anhthang.demo.repository;

import anhthang.demo.dto.AccountDTO;
import anhthang.demo.helper.jdbcMapper.AccountDTOMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
//import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AccountRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer addAccount(AccountDTO accountDTO){
        String sql = "Insert into account(accountID, email, display, password, role,avatar) values(?,?,?,?,?,?);";
        String uuid = UUID.randomUUID().toString();
        String hashPassword = BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt(10));
        Object[] params = {uuid, accountDTO.getEmail(), accountDTO.getDisplay(), hashPassword, "USER", accountDTO.getAvatar()};
        return jdbcTemplate.update(sql, params);
    }

    public Integer checkAccountExisted(String display) {
        String sql = "select exists (select * from Account where display = ?;";
        Object[] params = {display};
        return jdbcTemplate.update(sql, params);
    }

    public AccountDTO getAccountByEmail(String email){
        String sql = "select * from account where email = ?;";
        Object[] params = {email};
        return (AccountDTO) jdbcTemplate.queryForObject(sql, new AccountDTOMapper(), params);
    }

    public AccountDTO getAccountByAccountID(String accountID){
        String sql = "select * from account where accountID = ?;";
        Object[] params = {accountID};
        return (AccountDTO) jdbcTemplate.queryForObject(sql, new AccountDTOMapper(), params);
    }

    public Integer checkAccoutByEmail(String email){
        String sql = "select * from account where email = ?;";
        Object[] params = {email};
        return jdbcTemplate.update(sql, params);
    }

//    public Boolean addAccount(AccountDTO accountDTO){
//
//    }



}
