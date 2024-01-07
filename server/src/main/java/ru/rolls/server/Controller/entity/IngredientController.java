package ru.rolls.server.controller.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.rolls.server.entity.Ingredient;
import ru.rolls.server.service.entity.IngredientService;

@Controller
@RequestMapping("/work/ingredient")
@PreAuthorize("hasAuthority('ADMIN')")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<Iterable<Ingredient>> getIngredients() {
        return ResponseEntity.ok(ingredientService.getIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(
            @PathVariable(name = "id", required = true) Long id) {
        Optional<Ingredient> optIng = ingredientService.getIngredient(id);

        if (optIng.isPresent()) {
            return new ResponseEntity<>(optIng.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(
            @RequestBody Ingredient ingredient) {
        return new ResponseEntity<>(ingredientService.addIngredient(ingredient), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(
            @PathVariable(name = "id", required = true) Long id) {

        HashMap<String, Boolean> hm = ingredientService.removeIngredient(id);

        boolean isExists = hm.get("isExists");
        boolean havePositions = hm.get("havePositions");

        if (isExists) {
            if (havePositions) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
