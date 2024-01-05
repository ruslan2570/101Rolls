package ru.rolls.server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import ru.rolls.server.Entity.Client;
import ru.rolls.server.Entity.Employee;
import ru.rolls.server.Entity.Token;
import ru.rolls.server.Model.AuthenticationResponse;
import ru.rolls.server.Model.EmployeeAuthenticationRequest;
import ru.rolls.server.Repo.ClientRepo;
import ru.rolls.server.Repo.EmployeeRepo;
import ru.rolls.server.Repo.TokenRepo;

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
        AuthenticationManager authenticationManager;

        Logger logger = LoggerFactory.getLogger(getClass());

        public AuthenticationResponse authenticateEmployee(EmployeeAuthenticationRequest request) {
                logger.debug("аутентификация");

                authenticationManager.authenticate(
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
