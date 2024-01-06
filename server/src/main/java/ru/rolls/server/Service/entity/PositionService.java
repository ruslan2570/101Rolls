package ru.rolls.server.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rolls.server.repo.PositionRepo;

@Service
public class PositionService {
    
    @Autowired
    private PositionRepo positionRepo;

    
}
