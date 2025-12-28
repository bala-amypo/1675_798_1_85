// package com.example.demo.controller;

// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.LoginRequest;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {
    
//     @Autowired
//     private AuthenticationManager authenticationManager;
    
//     @Autowired
//     private UserRepository userRepository;
    
//     @Autowired
//     private PasswordEncoder passwordEncoder;
    
//     @Autowired
//     private JwtTokenProvider tokenProvider;
    
//     @PostMapping("/register")
//     public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
//         if (userRepository.existsByEmail(request.getEmail())) {
//             return ResponseEntity.badRequest().body("Email already exists!");
//         }
        
//         User user = new User();
//         user.setName(request.getName());
//         user.setEmail(request.getEmail());
//         user.setPassword(passwordEncoder.encode(request.getPassword()));
//         user.setRole(request.getRole());
        
//         User savedUser = userRepository.save(user);
        
//         Authentication authentication = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//         );
        
//         String jwt = tokenProvider.generateToken(authentication, savedUser);
//         return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(jwt));
//     }
    
//     @PostMapping("/login")
//     public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
//         Authentication authentication = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//         );
        
//         User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
//         String jwt = tokenProvider.generateToken(authentication, user);
        
//         return ResponseEntity.ok(new AuthResponse(jwt));
//     }
// }
package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;

    public AuthController(UserRepository userRepo,
                          PasswordEncoder encoder,
                          AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authManager = authManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setRole(req.getRole());
        user.setPassword(encoder.encode(req.getPassword()));
        userRepo.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("token", "dummy-token"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                req.getEmail(), req.getPassword()
            )
        );
        return ResponseEntity.ok(Map.of("token", "dummy-token"));
    }
}
