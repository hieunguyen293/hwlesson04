package anhthang.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ReceiptRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer addReceipt(String receiptID, String accountID, int total_money, String status){
       String sql = "insert into receipt(receiptID, accountID, total_money, status) values(?,?,?,?);";
       Object[] params = {receiptID, accountID, total_money, status};
       return jdbcTemplate.update(sql,params);
    }

    public Integer addReceiptDetail(String receiptID,String productID, int amount){
        String sql = "insert into receiptdetail(receiptID,productID,amount) values(?,?,?)";
        Object[] params = {receiptID,productID,amount};
        return jdbcTemplate.update(sql,params);
    }



//    public ReceiptInfor showInforAll(){
//        String sql = "SELECT receiptID, display,`status`,total_money from receipt " +
//                "inner join `account` where receipt.accountID=`account`.accountID";
//        return jdbcTemplate.queryForObject(sql,new ReceiptInforMapper());
//    }

}
