package ru.rolls.server.repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.Order;

public interface OrderRepo extends CrudRepository<Order, Long>{
    
}
