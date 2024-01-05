package ru.rolls.server.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.rolls.server.Model.EmployeeAuthenticationRequest;
import ru.rolls.server.Model.AuthenticationResponse;
import ru.rolls.server.Service.AuthenticationService;

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
