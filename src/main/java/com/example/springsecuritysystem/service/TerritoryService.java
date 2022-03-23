package com.example.springsecuritysystem.service;



import com.example.springsecuritysystem.entity.Teritory;

import java.util.Optional;

public interface TerritoryService {
    Optional<Teritory> getTerritoryById(long id);


}
