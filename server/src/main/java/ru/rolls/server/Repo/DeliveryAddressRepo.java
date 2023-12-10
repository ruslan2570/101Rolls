package ru.rolls.server.Repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.DeliveryAddress;

public interface DeliveryAddressRepo extends CrudRepository<DeliveryAddress, Long> {
    
}
