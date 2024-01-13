package ru.rolls.server.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = null;

        if (isPhoneNumber(username)) {
            Client client = clientRepo.findByPhone(username).orElseThrow(() -> new UsernameNotFoundException("User isn't found1"));

            user = client;
        } else {
            Employee employee = employeeRepo.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Employee isn't found1"));

            user = employee;
        }


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
