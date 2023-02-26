package com.testproject.todoapi.exceptions;

public class WrongCredentials extends RuntimeException {
    private final String errorCode = "401";

    public WrongCredentials(String message) {
        super(message);
    }
}
