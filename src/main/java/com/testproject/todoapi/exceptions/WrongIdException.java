package com.testproject.todoapi.exceptions;

import lombok.Getter;

@Getter
public class WrongIdException extends RuntimeException {
    private final String errorCode = "400";

    public WrongIdException(String message) {
        super(message);
    }
}
