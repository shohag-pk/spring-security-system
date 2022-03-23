package com.example.springsecuritysystem.repository;


import com.example.springsecuritysystem.entity.system.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByIdIn(Set<Integer> ids);
}
