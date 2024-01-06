package ru.rolls.server.repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.Position;

public interface PositionRepo extends CrudRepository<Position, Long> {
    
}
