package ru.rolls.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import ru.rolls.server.entity.Client;
import ru.rolls.server.entity.Employee;
import ru.rolls.server.entity.Token;
import ru.rolls.server.model.AuthenticationResponse;
import ru.rolls.server.model.EmployeeAuthenticationRequest;
import ru.rolls.server.repo.ClientRepo;
import ru.rolls.server.repo.EmployeeRepo;
import ru.rolls.server.repo.TokenRepo;

@Service
public class AuthenticationService {

        @Autowired
        ClientRepo clientRepo;

        @Autowired
        EmployeeRepo employeeRepo;

        @Autowired
        TokenRepo tokenRepo;

        @Autowired
        JwtService jwtService;

        @Autowired
        AuthenticationProvider authenticationProvider;


        public AuthenticationResponse authenticateEmployee(EmployeeAuthenticationRequest request) {

                authenticationProvider.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getLogin(),
                                                request.getPassword()));
                Employee employee = employeeRepo.findByLogin(request.getLogin())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(employee);
                saveUserToken(employee, jwtToken);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        private void saveUserToken(Employee employee, String jwtToken) {
                Token token = Token.builder()
                                .employee(employee)
                                .token(jwtToken)
                                .expired(false)
                                .revoked(false)
                                .build();
                tokenRepo.save(token);
        }

        private void saveUserToken(Client client, String jwtToken) {
                Token token = Token.builder()
                                .client(client)
                                .token(jwtToken)
                                .expired(false)
                                .revoked(false)
                                .build();
                tokenRepo.save(token);
        }

}
