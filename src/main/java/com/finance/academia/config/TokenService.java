package com.finance.academia.config;

import com.finance.academia.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenService {

    private final String secretKey = "my-super-secret-key-for-jwt-authentication-2026";

    public String generateToken(Usuario usuario){

        return Jwts.builder()
                .subject(usuario.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))// Token válido por 1 hora
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String ExtractUserName(String token){
        return extractClaims(token.trim()).getSubject();
    }

    public boolean isTokenExpiration(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String userName){
        final String extractUserName = ExtractUserName(token);
        return (extractUserName.equals(userName) && !isTokenExpiration(token));
    }
}
