package ru.rolls.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.rolls.server.service.AuthenticationService;
import ru.rolls.server.model.AuthenticationResponse;
import ru.rolls.server.model.EmployeeAuthenticationRequest;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/employee")
    public ResponseEntity<AuthenticationResponse> employeeAuthenticate(
        @RequestBody EmployeeAuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticateEmployee(request));
    }

}
