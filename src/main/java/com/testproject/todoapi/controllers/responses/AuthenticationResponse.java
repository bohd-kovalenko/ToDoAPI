package com.testproject.todoapi.controllers.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;

    @JsonCreator
    public AuthenticationResponse(@JsonProperty("jwt_token") String token) {
        this.token = token;
    }
}
