package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Basic CRUD comes out of the box
    // For example, we are using findById(id) in the UserService, but never define explicitly here, it can derive from name

    // Custom finder by username
    Optional<User> findByUsername(String username);

    // Partial match (case-insensitive)
    List<User> findByUsernameContainingIgnoreCase(String partial);

    // Count how many users have a certain name
    long countByUsername(String username);

    // Check if a user exists
    boolean existsByUsername(String username);



    // JPQL (entity field names)
    // Works with entity classes and field names, not table/column names
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByExactUsername(@Param("username") String username);

    // Native SQL (PostgreSQL-specific, for case-insensitive partial match)
    // Can just use raw SQL - especially useful for stored procedures or database-specific syntax
    @Query(value = "SELECT * FROM users WHERE username ILIKE %:partial%", nativeQuery = true)
    List<User> searchByUsernameNative(@Param("partial") String partial);

    @Procedure(name = "get_usernames()")
    List<String> getUsernamesFromProcedure();

    @Procedure(procedureName = "log_username_activity")
    void logUsernameActivity(String usernameInput);

}
