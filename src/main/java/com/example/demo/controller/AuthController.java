package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret:SecretKeyForJWTGeneration}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs:86400000}") // 1 day
    private int jwtExpirationMs;

    // Generate JWT using user info
    public String generateToken(String email, String role, Long userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Get email from token
    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    // Get role from token
    public String getRoleFromToken(String token) {
        return (String) Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().get("role");
    }

    // Get userId from token
    public Long getUserIdFromToken(String token) {
        return ((Number) Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().get("userId")).longValue();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.err.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.err.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.err.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.err.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.err.println("JWT claims string is empty");
        }
        return false;
    }
}
