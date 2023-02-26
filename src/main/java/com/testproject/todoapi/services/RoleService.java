package com.testproject.todoapi.services;

import com.testproject.todoapi.models.Role;
import com.testproject.todoapi.models.User;

public interface RoleService {
    Role createRoleForUser(User user);
}
