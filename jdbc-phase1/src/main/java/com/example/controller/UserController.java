package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users/1
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // GET /api/users/validate/1
    @GetMapping("/validate/{id}")
    public String validateCustEmail(@PathVariable int id) {
        return userService.validateCustEmail(id);
    }

    // GET /api/users/search/exact?username=alice
    @GetMapping("/search/exact")
    public Optional<User> findByExactUsername(@RequestParam String username) {
        return userService.findByExactUsername(username);
    }

    // GET /api/users/search/native?pattern=al
    @GetMapping("/search/native")
    public List<User> searchByUsernameNative(@RequestParam String pattern) {
        return userService.searchByUsernameNative(pattern);
    }


}