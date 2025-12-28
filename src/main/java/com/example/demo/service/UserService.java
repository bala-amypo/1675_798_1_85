package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User createUser(User user);
    User findByEmail(String email);
}