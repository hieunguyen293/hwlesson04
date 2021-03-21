package anhthang.demo.controller;

import anhthang.demo.dto.login.LoginRequestDTO;
import anhthang.demo.dto.login.LoginResponseDTO;
import anhthang.demo.exception.ApiException;
import anhthang.demo.service.AccountService;
import anhthang.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    AuthService authService;

    // login
    @GetMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO)throws ApiException {
        return authService.login(loginRequestDTO);


        // kiem tra xem co ton tai hay khong => neu co querry lay ra ID nguoi dung do
        // gen ra 1 token bat ky (uuid)
        // luu userID va token tuong ung vao database
        // tra ve key cho nguoi dung

    }
    // log out
    @GetMapping("/logout")
    public Boolean logout(@RequestParam String token) throws Exception {
        return authService.logout(token);
    }

    //
}
