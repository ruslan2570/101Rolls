package ru.rolls.server.repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.DeliveryAddress;

public interface DeliveryAddressRepo extends CrudRepository<DeliveryAddress, Long> {
    
}
