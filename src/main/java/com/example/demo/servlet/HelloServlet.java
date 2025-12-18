src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── demo
│   │
│   │               ├── DemoApplication.java
│   │
│   │               ├── servlet
│   │               │   └── HelloServlet.java
│   │
│   │               ├── config
│   │               │   ├── SecurityConfig.java
│   │               │   ├── WebConfig.java
│   │               │   └── SwaggerConfig.java
│   │
│   │               ├── controller
│   │               │   ├── AuthController.java
│   │               │   ├── PropertyController.java
│   │               │   ├── FacilityScoreController.java
│   │               │   ├── RatingController.java
│   │               │   └── RatingLogController.java
│   │
│   │               ├── dto
│   │               │   ├── RegisterRequest.java
│   │               │   ├── LoginRequest.java
│   │               │   └── AuthResponse.java
│   │
│   │               ├── entity
│   │               │   ├── User.java
│   │               │   ├── Property.java
│   │               │   ├── FacilityScore.java
│   │               │   ├── RatingResult.java
│   │               │   └── RatingLog.java
│   │
│   │               ├── repository
│   │               │   ├── UserRepository.java
│   │               │   ├── PropertyRepository.java
│   │               │   ├── FacilityScoreRepository.java
│   │               │   ├── RatingResultRepository.java
│   │               │   └── RatingLogRepository.java
│   │
│   │               ├── service
│   │               │   ├── UserService.java
│   │               │   ├── PropertyService.java
│   │               │   ├── FacilityScoreService.java
│   │               │   ├── RatingService.java
│   │               │   └── RatingLogService.java
│   │
│   │               ├── serviceImpl
│   │               │   ├── UserServiceImpl.java
│   │               │   ├── PropertyServiceImpl.java
│   │               │   ├── FacilityScoreServiceImpl.java
│   │               │   ├── RatingServiceImpl.java
│   │               │   └── RatingLogServiceImpl.java
│   │
│   │               ├── security
│   │               │   ├── CustomUserDetailsService.java
│   │               │   ├── JwtAuthenticationEntryPoint.java
│   │               │   ├── JwtAuthenticationFilter.java
│   │               │   └── JwtTokenProvider.java
│   │
│   │               └── exception
│   │                   ├── ApiError.java
│   │                   ├── BadRequestException.java
│   │                   ├── ResourceNotFoundException.java
│   │                   └── GlobalExceptionHandler.java
│
│   └── resources
│       ├── application.properties
│       └── application.yml
│
├── test
│   └── resources
│       └── application-test.properties
