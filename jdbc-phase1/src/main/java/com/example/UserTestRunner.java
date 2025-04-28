package com.example;

import com.example.service.UserService;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestRunner implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserTestRunner(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        int testId = 1;

        // Testing UserService (Standard get by ID)
        User user = userService.getUserById(testId);
        if (user != null) {
            System.out.println("Fetched user using UserService: " + user.getUsername());
        } else {
            System.out.println("User not found using UserService.");
        }

        // Testing UserRepository with Exact Username (JPQL query)
        userRepository.findByExactUsername("alice").ifPresentOrElse(
                u -> System.out.println("Found exact user using JPQL: " + u.getUsername()),
                () -> System.out.println("Exact match not found using JPQL.")
        );

        // Testing UserRepository with Native SQL (Search by partial username)
        List<User> results = userRepository.searchByUsernameNative("al");
        if (!results.isEmpty()) {
            System.out.println("Native match found using SQL:");
            results.forEach(u -> System.out.println("User: " + u.getUsername()));
        } else {
            System.out.println("No native matches found using SQL.");
        }

        // Testing that error gets written to the error DB
        String result = userService.validateCustEmail(1234);
        System.out.println("Validation result: " + result);


        // Inserting into table using stored procedure
        userRepository.logUsernameActivity("user_99");

    }
}
