package com.example.service;

import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserById(int id);
    String validateCustEmail(int id);
    Optional<User> findByExactUsername(String username);
    List<User> searchByUsernameNative(String pattern);
}
