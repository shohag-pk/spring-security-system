package com.example.springsecuritysystem.repository;

import com.example.springsecuritysystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
