package ru.rolls.server.service.entity;

import java.util.HashMap;
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

    public Iterable<Ingredient> getIngredients() {
        return ingredientRepo.findAll();
    }

    public Optional<Ingredient> getIngredient(Long id) {
        return ingredientRepo.findById(id);
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }

    public HashMap<String, Boolean> removeIngredient(Long id) {
        HashMap<String, Boolean> hm = new HashMap<>();

        boolean isExists = ingredientRepo.existsById(id);
        boolean havePositions = false;

        if(isExists){
            Ingredient ing = ingredientRepo.findById(id).orElse(null);
            if(ing.getPositions() != null || ing.getPositions().size() != 0){
                havePositions = true;
            } else{
                ingredientRepo.deleteById(id);
            }
        }
        
        hm.put("isExists", isExists);
        hm.put("havePositions", havePositions);

        return hm;
    }

}
