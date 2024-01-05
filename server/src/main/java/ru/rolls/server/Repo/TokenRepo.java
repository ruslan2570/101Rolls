package ru.rolls.server.Repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Token;

public interface TokenRepo  extends CrudRepository<Token, Long> {

    Token findByToken(String token);
    
}
