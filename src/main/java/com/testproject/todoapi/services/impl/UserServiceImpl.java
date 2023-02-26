package com.testproject.todoapi.services.impl;

import com.testproject.todoapi.exceptions.WrongCredentials;
import com.testproject.todoapi.models.User;
import com.testproject.todoapi.repositories.UserRepository;
import com.testproject.todoapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new WrongCredentials("Wrong username was given"));
    }

    @Override
    public User saveUserIntoDatabase(User user) {
        return userRepository.save(user);
    }
}
