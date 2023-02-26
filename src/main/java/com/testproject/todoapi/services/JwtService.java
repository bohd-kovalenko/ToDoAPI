package com.testproject.todoapi.services;

import com.testproject.todoapi.models.User;

public interface JwtService {
    String generateJWTForUser(User user);

    void verifyJwtSignature(String jwtToken, String username);

    String extractUsernameFromJWT(String jwt);
}
