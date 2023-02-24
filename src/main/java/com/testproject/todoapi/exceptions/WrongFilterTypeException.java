package com.testproject.todoapi.exceptions;

import lombok.Getter;

@Getter
public class WrongFilterTypeException extends RuntimeException {
    private final String errorCode = "400";

    public WrongFilterTypeException(String message) {
        super(message);
    }
}
