package anhthang.demo.service;

import anhthang.demo.constant.JwtConstant;
import anhthang.demo.dto.AccountDTO;
import anhthang.demo.dto.SessionDTO;
import anhthang.demo.dto.login.LoginRequestDTO;
import anhthang.demo.dto.login.LoginResponseDTO;
import anhthang.demo.helper.jdbcMapper.Jwt;
import anhthang.demo.repository.AccountRepository;
import anhthang.demo.repository.AuthRepository;
import anhthang.demo.repository.SessionRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SessionRepository sessionRepository;

    public Integer checkSessionByToken(String token){
        try {
            return authRepository.checkSessionByToken(token);
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public SessionDTO getSessionByToken(String token){
        try {
            return authRepository.getSessionByToken(token);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){

        if (accountRepository.checkAccoutByEmail(loginRequestDTO.getEmail()) != 0){
            AccountDTO accountDTO = accountRepository.getAccountByEmail(loginRequestDTO.getEmail());
            String password = loginRequestDTO.getPassword();
            String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));


            if (accountDTO.getPassword().equals(hashPassword)){

                String userID = accountDTO.getAccountID();
                Jwt jwt = new Jwt();
                String token = jwt.generateToken(userID, JwtConstant.SECRET_KEY, JwtConstant.JWT_EXPIRATION);
                SessionDTO sessionDTO = new SessionDTO(userID, token);
                sessionRepository.addSession(sessionDTO);
                LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token);
                return loginResponseDTO;
            }else {
                System.out.println("Sai mat khau");
            }
        }else{
            System.out.println("Sai tai khoan");
        }
        return null;
    }

    public Boolean logout(String token) throws Exception{
        if (authRepository.logout(token) != 0){
            return true;
        }else {
            throw new Exception("Error");
        }
    }


}
