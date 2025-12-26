package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secret = "verySecretKeyChangeMe";
    private final long validityInMs = 3600000L;

    public String generateToken(org.springframework.security.core.Authentication auth, User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims c = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return c.getSubject();
    }

    public Long getUserIdFromToken(String token) {
        Claims c = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return c.get("userId", Long.class);
    }
}
