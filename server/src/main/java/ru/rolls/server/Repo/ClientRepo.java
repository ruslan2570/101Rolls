package ru.rolls.server.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Client;

public interface ClientRepo extends CrudRepository<Client, Long>{
    
    public Optional<Client> findByPhone(String phone);
}
