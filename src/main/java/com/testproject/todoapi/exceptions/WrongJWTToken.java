package com.testproject.todoapi.exceptions;

public class WrongJWTToken extends RuntimeException {
    private final String errorCode = "401";

    public WrongJWTToken(String message) {
        super(message);
    }
}
