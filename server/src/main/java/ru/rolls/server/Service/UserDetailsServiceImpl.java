package ru.rolls.server.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.rolls.server.entity.Client;
import ru.rolls.server.entity.Employee;
import ru.rolls.server.repo.ClientRepo;
import ru.rolls.server.repo.EmployeeRepo;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    EmployeeRepo employeeRepo;

        Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = null;

        if (isPhoneNumber(username)) {
            Client client = clientRepo.findByPhone(username).get();

            user = client;
        } else {
            Employee employee = employeeRepo.findByLogin(username).get();

            user = employee;
        }

        logger.debug("Здесь UserDetails");


        if (user == null)
            throw new UsernameNotFoundException("Пользователь не найден");

        return user;

    }

    private boolean isPhoneNumber(String login) {
        // Простой пример для проверки, что строка состоит из цифр и имеет длину 11
        // символов
        return login != null && login.matches("\\d{11}");
    }

}
