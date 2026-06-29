package com.anshbkeai.issuetracker.core.service;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anshbkeai.issuetracker.core.model.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JWTService {

    private SecretKey secretKey;

    public JWTService(@Value("${JWT_SECRET_BASE}") String jwtSecret) {
        try {
            byte[] bs = Base64.getDecoder().decode(jwtSecret);
            this.secretKey = new SecretKeySpec(bs, "HmacSHA256");
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public String generateJwt(String username, Role role) {

        return Jwts.builder()
                .subject(username)
                .claim("role", role.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Duration.ofHours(2).toMillis()))
                .signWith(secretKey)
                .compact();
    }

    public boolean validate(String token) {
        return    !isTokenExpired(token);  
    }
    private  boolean  isTokenExpired(String  token) {
        final  Claims  claims  =  Jwts
                                    .parser()
                                    .verifyWith(secretKey)
                                    .build()
                                    .parseSignedClaims(token)
                                    .getPayload();
        return  claims.getExpiration().before(new  Date());

    }
    public Role extractRole(String token) {
        String role = Jwts.parser()
                            .verifyWith(secretKey)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload()
                            .get("role", String.class);

        return Role.valueOf(role);
    }
    public String extractUsername(String token) {
        return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
    }

}
