package com.example.springsecuritysystem.service.implementation;


import com.example.springsecuritysystem.entity.system.user.Permission;
import com.example.springsecuritysystem.entity.system.user.Role;
import com.example.springsecuritysystem.projection.PermissionIdAndName;
import com.example.springsecuritysystem.repository.PermissionRepository;
import com.example.springsecuritysystem.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getAllPermission() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Permission> getAppPermissionByIds(Set<Integer> ids) {
        return permissionRepository.findAllByIdIn(ids);
    }

    @Override
    public List<Permission> getAllPermissionsByRolesIn(List<Role> roles) {
        return permissionRepository.findAllByRolesIn(roles);
    }

    @Override
    public List<PermissionIdAndName> getAllPermissionIdAndName() {
        return permissionRepository.findAllPermissionIdAndName();
    }

    @Override
    public List<PermissionIdAndName> getAllPermissionIdAndNameByRolesNotIn(List<Integer> roles) {
        return permissionRepository.findAllPermissionIdAndNameByRolesNotIn(roles);
    }

}
