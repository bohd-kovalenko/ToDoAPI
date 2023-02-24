package com.testproject.todoapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Error {
    @JsonProperty(value = "error_id")
    private String id;
    @JsonProperty(value = "error_text")
    private String errorText;

    @JsonCreator
    public Error(String id, String errorText) {
        this.id = id;
        this.errorText = errorText;
    }
}
