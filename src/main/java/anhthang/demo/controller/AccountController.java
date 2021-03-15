package anhthang.demo.controller;

import anhthang.demo.dto.AccountDTO;
import anhthang.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/add")
    public boolean addAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        return accountService.addAccount(accountDTO);
    }

}
