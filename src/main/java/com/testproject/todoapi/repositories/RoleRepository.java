package com.testproject.todoapi.repositories;

import com.testproject.todoapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
