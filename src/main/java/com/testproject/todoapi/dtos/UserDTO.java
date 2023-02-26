package com.testproject.todoapi.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;

    @JsonCreator
    public UserDTO(@JsonProperty(value = "username", required = true) String username, @JsonProperty(value = "password", required = true) String password) {
        this.username = username;
        this.password = password;
    }
}
