package ru.rolls.server.Repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Order;

public interface OrderRepo extends CrudRepository<Order, Long>{
    
}
