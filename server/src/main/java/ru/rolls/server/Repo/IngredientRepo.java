package ru.rolls.server.repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.Ingredient;

public interface IngredientRepo extends CrudRepository<Ingredient, Long>{
    
}
