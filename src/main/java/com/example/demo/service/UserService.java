package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public User createUser(User user) {
        if (user == null) {
            throw new BadRequestException("User cannot be null");
        }

        // Validate user fields
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        // Check for duplicate email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email is already in use: " + user.getEmail());
        }

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new BadRequestException("Email cannot be null or blank");
        }

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
}
