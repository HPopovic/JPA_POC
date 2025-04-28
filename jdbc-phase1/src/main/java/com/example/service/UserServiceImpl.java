package com.example.service;

import com.example.model.User;
import com.example.service.ErrorLogService;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final ErrorLogService errorLogService;


    public UserServiceImpl(UserRepository userRepository, ErrorLogService errorLogService) {
        this.userRepository = userRepository;
        this.errorLogService = errorLogService;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public String validateCustEmail(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            String email = user.get().getUseremail();
            return (email.matches(".+@example\\.com")) ? "Valid" : "Invalid";

        }else {
            errorLogService.logError("User with ID " + id + " not found during email validation.");
            return "Invalid";
        }

    }

    @Override
    public Optional<User> findByExactUsername(String username) {
        return userRepository.findByExactUsername(username);
    }

    @Override
    public List<User> searchByUsernameNative(String pattern) {
        return userRepository.searchByUsernameNative(pattern);
    }
}
