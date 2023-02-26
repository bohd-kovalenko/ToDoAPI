package com.testproject.todoapi.services;

import com.testproject.todoapi.models.User;

public interface UserService {
    User getUserByUsername(String username);
    User saveUserIntoDatabase(User user);
}
