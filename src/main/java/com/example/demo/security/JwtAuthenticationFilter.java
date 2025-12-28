// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.util.StringUtils;
// import org.springframework.web.filter.OncePerRequestFilter;
// import java.io.IOException;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
//     @Autowired
//     private JwtTokenProvider tokenProvider;
    
//     @Autowired
//     private CustomUserDetailsService customUserDetailsService;
    
//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {
//         try {
//             String jwt = getJwtFromRequest(request);
            
//             if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
//                 String userEmail = tokenProvider.getUserEmailFromToken(jwt);
//                 UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
                
//                 UsernamePasswordAuthenticationToken authentication = 
//                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//         } catch (Exception ex) {
//             logger.error("Could not set user authentication in security context", ex);
//         }
        
//         filterChain.doFilter(request, response);
//     }
    
//     private String getJwtFromRequest(HttpServletRequest request) {
//         String bearerToken = request.getHeader("Authorization");
//         if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//             return bearerToken.substring(7);
//         }
//         return null;
//     }
// }
package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                   CustomUserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // NO TOKEN → DO NOT AUTHENTICATE → let Spring return 401
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            String email = jwtTokenProvider.getEmailFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception ex) {
            // INVALID TOKEN → CLEAR CONTEXT → 401
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
