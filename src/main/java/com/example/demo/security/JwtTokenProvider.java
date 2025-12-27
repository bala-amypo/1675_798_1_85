package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    // Use externalized secret via application.properties
    @Value("${app.jwtSecret:secret-key}")
    private String secret;

    @Value("${app.jwtExpirationMs:86400000}") // default 1 day
    private long jwtExpirationMs;

    // Generate JWT from user info
    public String generateToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Extract email from token
    public String getEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    // Extract userId from token
    public Long getUserIdFromToken(String token) {
        return parseClaims(token).get("userId", Number.class).longValue();
    }

    // Extract role from token
    public String getRoleFromToken(String token) {
        return parseClaims(token).get("role", String.class);
    }

    // Validate JWT
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Optionally log error
            System.err.println("Invalid JWT: " + e.getMessage());
        }
        return false;
    }

    // Helper method to parse claims safely
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
