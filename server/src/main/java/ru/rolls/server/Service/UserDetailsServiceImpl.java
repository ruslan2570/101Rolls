package ru.rolls.server.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.rolls.server.Entity.Client;
import ru.rolls.server.Entity.Employee;
import ru.rolls.server.Repo.ClientRepo;
import ru.rolls.server.Repo.EmployeeRepo;


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        

        String login = username;
        String password = null;

        List<GrantedAuthority> roles = new ArrayList<>();
        

        if(isPhoneNumber(username)){
            Client client = clientRepo.findByPhone(username);
            
            if(client != null){
                password = "N/A";
                roles.add(new SimpleGrantedAuthority("CLIENT"));
            }
        } else{
            Employee employee = employeeRepo.findByLogin(username);

            if(employee != null){
                password = employee.getPassword();
                roles.add(new SimpleGrantedAuthority(employee.getRole().toString()));
            }
        }


        if(password == null)
            throw new UsernameNotFoundException("Пользователь не найден");

        return new User(login, password, roles);
       
    }

    private boolean isPhoneNumber(String login) {
        // Простой пример для проверки, что строка состоит из цифр и имеет длину 11 символов
        return login != null && login.matches("\\d{11}");
    }
    


    
}
