package org.example.java6_asm.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.example.java6_asm.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
