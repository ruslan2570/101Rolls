package ru.rolls.server.model.dto.employee;

import lombok.Data;
import ru.rolls.server.entity.Employee;

@Data
public class EmployeeDTO {
    
    private Long id;

    private String firstname;

    private String lastname;

    private String patronymic;

    private String role;

    private String roleName;

    private String login;

    public EmployeeDTO(Employee employee){
        id = employee.getId();
        firstname = employee.getFirstname();
        lastname = employee.getLastname();
        patronymic = employee.getPatronymic();
        role = employee.getRole().name();
        roleName = employee.getRole().getRoleName();
        login = employee.getLogin();
    }
}
