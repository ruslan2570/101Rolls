package ru.rolls.server.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.entity.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long>{
    
    public Optional<Employee> findByLogin(String login);
}
