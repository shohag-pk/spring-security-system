package com.example.springsecuritysystem.service;


import com.example.springsecuritysystem.entity.system.user.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role saveRole(Role role);

    List<Role> getAllRole();

    List<Role> getAppRoleByIds(Set<Integer> ids);
}
