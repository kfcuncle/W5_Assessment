package com.example.W5Assessment.exception;

public class EmptyTaskListException extends RuntimeException{
    private String message;

    public EmptyTaskListException() {
    }

    public EmptyTaskListException(String msg) {
        super(msg);
        this.message = msg;
    }
}
