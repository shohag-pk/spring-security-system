package com.example.springsecuritysystem.repository;

import com.example.springsecuritysystem.entity.system.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserNameIgnoreCase(String username);

    Optional<Users> findByEmailIgnoreCase(String email);
    Optional<Users> findByTeritory_IdAndIsAccountNonExpired(long territoryId, boolean isAccountNonExpired);


}
