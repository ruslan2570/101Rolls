package ru.rolls.server.Repo;

import org.springframework.data.repository.CrudRepository;

import ru.rolls.server.Entity.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long>{
    
}
