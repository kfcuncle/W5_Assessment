package com.example.W5Assessment.exception;

public class NoSuchTaskExistsException extends RuntimeException{
    private String message;

    public NoSuchTaskExistsException() {
    }

    public NoSuchTaskExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
