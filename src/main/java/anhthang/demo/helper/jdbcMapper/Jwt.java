package anhthang.demo.helper.jdbcMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;


public class Jwt {
    public static String generateToken(String userID, String secretKey, long JWT_EXPIRATION){
        Date now = new Date();

        Date expriredTime = new Date(now.getTime() + JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(userID)
                .setIssuedAt(now)
                .setExpiration(expriredTime)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;

    }

    public static String verifyToken(String token, String secretKey){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
