package com.testproject.todoapi.models.adapters;

import com.testproject.todoapi.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class GrantedAuthorityAdapter implements GrantedAuthority {
    private final Role role;

    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}
