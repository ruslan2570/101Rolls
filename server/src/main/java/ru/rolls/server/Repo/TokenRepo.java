package ru.rolls.server.repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.Token;

public interface TokenRepo  extends CrudRepository<Token, Long> {

    Token findByToken(String token);
    
}
