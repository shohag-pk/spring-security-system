package com.example.springsecuritysystem.repository;


import com.example.springsecuritysystem.entity.Teritory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TeritoryRepository extends JpaRepository<Teritory, Long> {
    List<Teritory> findAllByParentId(BigDecimal id);


}
