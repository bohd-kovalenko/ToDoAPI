package com.testproject.todoapi.exceptions;

import lombok.Getter;

@Getter
public class WrongTaskFieldsException extends RuntimeException {
    private final String errorCode = "400";

    public WrongTaskFieldsException(String message) {
        super(message);
    }
}
