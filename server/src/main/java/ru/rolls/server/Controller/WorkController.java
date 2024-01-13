package ru.rolls.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.rolls.server.entity.Employee;
import ru.rolls.server.model.dto.employee.EmployeeDTO;

@Controller
@RequestMapping("/work")

public class WorkController {
    
    @GetMapping("/me")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal Employee employee){
        EmployeeDTO dto = new EmployeeDTO(employee);

        return ResponseEntity.ok(dto);       
    }

}
