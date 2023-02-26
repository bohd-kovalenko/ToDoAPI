package com.testproject.todoapi.services.impl;

import com.testproject.todoapi.models.Role;
import com.testproject.todoapi.models.User;
import com.testproject.todoapi.repositories.RoleRepository;
import com.testproject.todoapi.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role createRoleForUser(User user) {
        Role role = Role.builder().roleName(user.getUsername()).user(user).build();
        return roleRepository.save(role);
    }
}
