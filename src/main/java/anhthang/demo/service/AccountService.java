package anhthang.demo.service;

import anhthang.demo.dto.AccountDTO;
import anhthang.demo.dto.SessionDTO;
import anhthang.demo.model.Account;
import anhthang.demo.repository.AccountRepository;
import anhthang.demo.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    AccountRepository accountRepository;

    public Boolean addAccount(AccountDTO accountDTO) throws Exception{
        if (accountRepository.checkAccountExisted(accountDTO.getEmail()) == 0){
            if (accountRepository.addAccount(accountDTO) != 0){
                return true;
            }else{
                return false;
            }
        }else{
            System.out.println("Account exists");
            return false;
        }
    }



    public AccountDTO getAccountByAccountID(String accountID){
        try {
            return accountRepository.getAccountByAccountID(accountID);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public AccountDTO getAccountByEmail(String email){
        return null;
    }

}
