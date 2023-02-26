package com.testproject.todoapi.controllers;

import com.testproject.todoapi.controllers.responses.AuthenticationResponse;
import com.testproject.todoapi.dtos.UserDTO;
import com.testproject.todoapi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authenticationService.registerNewUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authenticationService.loginExistingUser(userDTO));
    }

}
