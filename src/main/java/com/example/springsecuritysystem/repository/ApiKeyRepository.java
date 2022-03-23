package com.example.springsecuritysystem.repository;

import com.example.springsecuritysystem.entity.system.user.ApiKeys;
import com.example.springsecuritysystem.enumeration.KeyType;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ApiKeyRepository extends JpaRepository<ApiKeys, Integer> {

    List<ApiKeys> findAllByKeyTypeAndIsEnable(KeyType keyType, boolean enabled);

}
