package com.example.springsecuritysystem.service.implementation;


import com.example.springsecuritysystem.entity.Teritory;
import com.example.springsecuritysystem.repository.TeritoryRepository;
import com.example.springsecuritysystem.service.TerritoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TerritoryServiceImpl implements TerritoryService {

    private final TeritoryRepository teritoryRepository;

    public TerritoryServiceImpl(TeritoryRepository teritoryRepository) {
        this.teritoryRepository = teritoryRepository;
    }


    @Override
    public Optional<Teritory> getTerritoryById(long id) {
        return teritoryRepository.findById(id);
    }



}
