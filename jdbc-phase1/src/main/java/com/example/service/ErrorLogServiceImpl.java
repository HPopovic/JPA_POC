package com.example.service;

import com.example.model.ErrorLog;
import com.example.repository.ErrorLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository errorLogRepository;

    public ErrorLogServiceImpl(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    @Override
    public void logError(String message) {
        int updated = errorLogRepository.updateTimestampIfExists(message);

        if (updated == 0) {
            ErrorLog log = new ErrorLog();
            log.setMessage(message);
            log.setTimestamp(LocalDateTime.now());
            errorLogRepository.save(log);
        }
    }

}
