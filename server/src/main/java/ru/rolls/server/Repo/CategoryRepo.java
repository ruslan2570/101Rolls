package ru.rolls.server.Repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Category;

public interface CategoryRepo extends CrudRepository<Category, Long>{
    
    
}
