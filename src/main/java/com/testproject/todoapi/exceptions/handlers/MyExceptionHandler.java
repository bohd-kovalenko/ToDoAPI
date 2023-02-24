package com.testproject.todoapi.exceptions.handlers;

import com.testproject.todoapi.exceptions.WrongFilterTypeException;
import com.testproject.todoapi.exceptions.WrongIdException;
import com.testproject.todoapi.exceptions.WrongSortTypeException;
import com.testproject.todoapi.exceptions.WrongTaskFieldsException;
import com.testproject.todoapi.models.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = {WrongFilterTypeException.class})
    public ResponseEntity<Error> handleWrongFilterTypeException(WrongFilterTypeException e) {
        return ResponseEntity.status(Integer.parseInt(e.getErrorCode())).body(new Error(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(value = {WrongIdException.class})
    public ResponseEntity<Error> handleWrongIdException(WrongIdException e) {
        return ResponseEntity.status(Integer.parseInt(e.getErrorCode())).body(new Error(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(value = {WrongSortTypeException.class})
    public ResponseEntity<Error> handleWrongSortTypeException(WrongSortTypeException e) {
        return ResponseEntity.status(Integer.parseInt(e.getErrorCode())).body(new Error(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(value = {WrongTaskFieldsException.class})
    public ResponseEntity<Error> handleWrongTaskFieldsException(WrongTaskFieldsException e) {
        return ResponseEntity.status(Integer.parseInt(e.getErrorCode())).body(new Error(e.getErrorCode(), e.getMessage()));
    }
}
