package ru.rolls.server.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.Client;

public interface ClientRepo extends CrudRepository<Client, Long>{
    
    public Optional<Client> findByPhone(String phone);
}
