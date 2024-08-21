package com.example.ex31_jpa_qnaboard_rest_api_security.security;

import org.springframework.stereotype.Service;

import com.example.ex31_jpa_qnaboard_rest_api_security.user.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenProvider {
    private static final String SECRET_KEY = "IFb8IHK1O0eaUWWFWhZ5ZR64o2m6WmN8m3rEKTZHyPBOpffuYEGdW6bA07YWhPeDtQIoWNqjabWbYxjgnMXbAc6/WTx72KhcO3WW4TIMpqM=";
    
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String create(UserEntity userEntity) {
        
        Date expiryDate = Date.from(Instant.now().plus(1,ChronoUnit.DAYS));

        return Jwts.builder()
                .setSubject(String.valueOf(userEntity.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
        
        return claims.getSubject();
    }
}
