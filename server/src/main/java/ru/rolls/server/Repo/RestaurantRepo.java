package ru.rolls.server.Repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Restaurant;

public interface RestaurantRepo extends CrudRepository<Restaurant, Long>{
    
}
