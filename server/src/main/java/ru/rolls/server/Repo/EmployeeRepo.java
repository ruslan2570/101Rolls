package ru.rolls.server.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long>{
    
    public Optional<Employee> findByLogin(String login);
}
