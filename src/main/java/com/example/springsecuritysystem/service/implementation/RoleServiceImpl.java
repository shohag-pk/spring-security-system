package com.example.springsecuritysystem.service.implementation;


import com.example.springsecuritysystem.entity.system.user.Role;
import com.example.springsecuritysystem.repository.RoleRepository;
import com.example.springsecuritysystem.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getAppRoleByIds(Set<Integer> ids) {
        return roleRepository.findAllByIdIn(ids);
    }
}
