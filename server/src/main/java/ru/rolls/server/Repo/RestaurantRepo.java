package ru.rolls.server.repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.Restaurant;

public interface RestaurantRepo extends CrudRepository<Restaurant, Long>{
    
}
