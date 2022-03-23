package com.example.springsecuritysystem.service;


import com.example.springsecuritysystem.entity.system.user.Permission;
import com.example.springsecuritysystem.entity.system.user.Role;
import com.example.springsecuritysystem.projection.PermissionIdAndName;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    Permission savePermission(Permission permission);

    List<Permission> getAllPermission();

    List<Permission> getAppPermissionByIds(Set<Integer> ids);

    List<Permission> getAllPermissionsByRolesIn(List<Role> roles);

    List<PermissionIdAndName> getAllPermissionIdAndName();

    List<PermissionIdAndName> getAllPermissionIdAndNameByRolesNotIn(List<Integer> roles);
}
