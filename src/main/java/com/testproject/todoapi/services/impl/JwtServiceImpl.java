package com.testproject.todoapi.services.impl;

import com.testproject.todoapi.exceptions.WrongJWTToken;
import com.testproject.todoapi.models.User;
import com.testproject.todoapi.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final String key = "3273357638792F423F4528482B4D6251655468576D5A7133743677397A24432646294A404E635266556A586E327235753778214125442A472D4B615064536756";

    @Override
    public String generateJWTForUser(User user) {
        Date now = new Date();
        now.setTime(now.getTime() + 3600000);
        Key key = Keys.hmacShaKeyFor(this.key.getBytes());
        String jwtToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(now)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return jwtToken;
    }

    @Override
    public String extractUsernameFromJWT(String jwt) {
        try {
            Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).build().parse(jwt).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            throw new WrongJWTToken("Recieved jwt token is not valid");
        }
    }

    @SneakyThrows
    @Override
    public void verifyJwtSignature(String jwtToken, String username) {
        try {
            Key key = Keys.hmacShaKeyFor(this.key.getBytes());
            Claims claims = (Claims) Jwts.parserBuilder()
                    .setSigningKey(key)
                    .requireSubject(username)
                    .build()
                    .parse(jwtToken).getBody();
            if (claims.getExpiration().before(new Date())) throw new Exception();
        } catch (Exception e) {
            throw new WrongJWTToken("Recieved jwt token is not valid");
        }
    }

}
