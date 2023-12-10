package ru.rolls.server.Repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Position;

public interface PositionRepo extends CrudRepository<Position, Long> {
    
}
