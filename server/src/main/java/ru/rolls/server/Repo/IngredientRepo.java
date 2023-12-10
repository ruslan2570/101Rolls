package ru.rolls.server.Repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Ingredient;

public interface IngredientRepo extends CrudRepository<Ingredient, Long>{
    
}
