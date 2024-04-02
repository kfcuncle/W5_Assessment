package com.example.W5Assessment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = NoSuchTaskExistsException.class)
    public ResponseEntity<Object> noSuchPetExistsException(NoSuchTaskExistsException ex, WebRequest request) {
        // Log the exception
        logException(ex);

        // Return a response entity with an error message
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyTaskListException.class)
    public ResponseEntity<Object> EmptyTaskListException(EmptyTaskListException ex, WebRequest request) {
        // Log the exception
        logException(ex);

        // Return a response entity with an error message
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    // Method to log the exception to a text file
    private void logException(Exception ex) {
        logger.error("Error occurred while logging the exception: " + ex.getMessage());
//        try (FileWriter writer = new FileWriter("error.log", true);
//             PrintWriter printWriter = new PrintWriter(writer)) {
//            printWriter.println("Timestamp: " + LocalDateTime.now());
//            printWriter.println("Exception: " + ex.getMessage());
//            printWriter.println("----------------------------------------");
//        } catch (IOException e) {
//
//        }
    }

    // Custom error message class
    static class ErrorMessage {
        private final int statusCode;
        private final String message;
        private final LocalDateTime timestamp;

        public ErrorMessage(HttpStatus status, String message, LocalDateTime timestamp) {
            this.statusCode = status.value();
            this.message = message;
            this.timestamp = timestamp;
        }

        // Getters for JSON serialization
        public int getStatusCode() {
            return statusCode;
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
