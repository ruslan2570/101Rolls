package ru.rolls.server.service.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rolls.server.entity.Ingredient;
import ru.rolls.server.repo.IngredientRepo;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientRepo ingredientRepo;

    public Iterable<Ingredient> getIngredients(){
        return ingredientRepo.findAll();
    }

    public Optional<Ingredient> getIngredient(Long id){
        return ingredientRepo.findById(id);
    }

    public Ingredient addIngredient(Ingredient ingredient){
        return ingredientRepo.save(ingredient);
    }   

    public boolean removeIngredient(Long id){
        boolean isExists = ingredientRepo.existsById(id);
        ingredientRepo.deleteById(id);
        return isExists;
    }

}
