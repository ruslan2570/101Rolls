package ru.rolls.server.repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.Category;

public interface CategoryRepo extends CrudRepository<Category, Long>{
    
    
}
