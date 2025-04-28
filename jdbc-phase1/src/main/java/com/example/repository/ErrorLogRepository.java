package com.example.repository;

import com.example.model.ErrorLog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Integer> {

    Optional<ErrorLog> findByMessage(String message);

    @Modifying
    @Transactional
    @Query("UPDATE ErrorLog e SET e.timestamp = CURRENT_TIMESTAMP WHERE e.message = :message")
    int updateTimestampIfExists(String message);


}
