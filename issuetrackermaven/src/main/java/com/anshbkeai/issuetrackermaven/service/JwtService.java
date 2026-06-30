package com.anshbkeai.issuetrackermaven.service;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


import com.anshbkeai.issuetrackermaven.pojo.ROLE;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtService {

    private SecretKey secretKey;
    private final String jwtsecret="yl0rw5jDdTKgaUYB86cw/9xdzefS5to95U3tDnolFmg+";

    public JwtService() {
        try {
            byte[] bs = Base64.getDecoder().decode(jwtsecret);
            this.secretKey = new SecretKeySpec(bs, "HmacSHA256");
        }catch(Exception e) {
            System.exit(0);
        }
    }

    public String generateJwt(String username, ROLE role) {

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
    public ROLE extractRole(String token) {
        String role = Jwts.parser()
                            .verifyWith(secretKey)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload()
                            .get("role", String.class);

        return ROLE.valueOf(role);
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
