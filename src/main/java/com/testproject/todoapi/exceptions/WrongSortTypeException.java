package com.testproject.todoapi.exceptions;

import lombok.Getter;

@Getter
public class WrongSortTypeException extends RuntimeException {
    private final String errorCode = "400";

    public WrongSortTypeException(String message) {
        super(message);
    }
}
