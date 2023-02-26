package com.testproject.todoapi.services.impl;

import com.testproject.todoapi.controllers.responses.AuthenticationResponse;
import com.testproject.todoapi.dtos.UserDTO;
import com.testproject.todoapi.models.Role;
import com.testproject.todoapi.models.User;
import com.testproject.todoapi.services.AuthenticationService;
import com.testproject.todoapi.services.JwtService;
import com.testproject.todoapi.services.RoleService;
import com.testproject.todoapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse registerNewUser(UserDTO userDTO) {
        User user = User.builder()
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .username(userDTO.getUsername())
                .build();
        userService.saveUserIntoDatabase(user);
        roleService.createRoleForUser(user);
        return new AuthenticationResponse(jwtService.generateJWTForUser(user));
    }

    @Override
    public AuthenticationResponse loginExistingUser(UserDTO userDTO) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        User user = userService.getUserByUsername(userDTO.getUsername());
        return new AuthenticationResponse(jwtService.generateJWTForUser(user));
    }
}
