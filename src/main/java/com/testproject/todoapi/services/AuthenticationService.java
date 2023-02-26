package com.testproject.todoapi.services;

import com.testproject.todoapi.controllers.responses.AuthenticationResponse;
import com.testproject.todoapi.dtos.UserDTO;

public interface AuthenticationService {
    AuthenticationResponse registerNewUser(UserDTO userDTO);

    AuthenticationResponse loginExistingUser(UserDTO userDTO);
}
