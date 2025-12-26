// package com.example.demo.controller;

// import com.example.demo.dto.LoginRequest;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.security.JwtTokenProvider;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication")
// public class AuthController {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final AuthenticationManager authenticationManager;
//     private final JwtTokenProvider jwtTokenProvider;

//     public AuthController(UserRepository userRepository,
//                           PasswordEncoder passwordEncoder,
//                           AuthenticationManager authenticationManager,
//                           JwtTokenProvider jwtTokenProvider) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.authenticationManager = authenticationManager;
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @PostMapping("/register")
//     @Operation(summary = "Register new user")
//     public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest req) {
//         if (userRepository.findByEmail(req.getEmail()).isPresent()) {
//             throw new IllegalStateException("Email already in use");
//         }
//         User user = new User();
//         user.setName(req.getName());
//         user.setEmail(req.getEmail());
//         user.setPassword(passwordEncoder.encode(req.getPassword()));
//         user.setRole(req.getRole() != null ? req.getRole() : "ANALYST");
//         user = userRepository.save(user);

//         Authentication auth = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
//         );
//         String token = jwtTokenProvider.generateToken(auth, user);

//         Map<String, String> body = new HashMap<>();
//         body.put("token", token);
//         return ResponseEntity.status(HttpStatus.CREATED).body(body);
//     }

//     @PostMapping("/login")
//     @Operation(summary = "Login")
//     public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest req) {
//         Authentication auth = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
//         );
//         User user = userRepository.findByEmail(req.getEmail())
//                 .orElseThrow(() -> new IllegalStateException("User not found"));
//         String token = jwtTokenProvider.generateToken(auth, user);
//         Map<String, String> body = new HashMap<>();
//         body.put("token", token);
//         return ResponseEntity.ok(body);
//     }
// }
