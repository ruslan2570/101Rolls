package ru.rolls.server.service.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rolls.server.entity.Position;
import ru.rolls.server.repo.PositionRepo;

@Service
public class PositionService {

    @Autowired
    private PositionRepo positionRepo;

    public Iterable<Position> getPositions() {
        return positionRepo.findAll();
    }

    public Optional<Position> getPosition(Long id) {
        return positionRepo.findById(id);
    }

    public Position addOrUpdatePosition(Position position) {
        return positionRepo.save(position);
    }
 

}
