// package com.example.demo.security;

// import com.example.demo.entity.User;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;

// @Component
// public class JwtTokenProvider {

//     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//     public String generateToken(Authentication authentication, User user) {
//         Date now = new Date();
//         Date expiry = new Date(now.getTime() + 3600_000); // 1h
//         return Jwts.builder()
//                 .setSubject(user.getId().toString())
//                 .claim("email", user.getEmail())
//                 .claim("role", user.getRole())
//                 .setIssuedAt(now)
//                 .setExpiration(expiry)
//                 .signWith(key)
//                 .compact();
//     }

//     public Long getUserIdFromToken(String token) {
//         String subject = Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//         return Long.valueOf(subject);
//     }
// }
