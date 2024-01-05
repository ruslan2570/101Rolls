package ru.rolls.server.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static ru.rolls.server.Entity.EmployeeRole.ADMIN;
import static ru.rolls.server.Entity.EmployeeRole.DELIVERER;
import static ru.rolls.server.Entity.EmployeeRole.OPERATOR;
import static ru.rolls.server.Entity.EmployeeRole.CHEF;

import ru.rolls.server.AuthProvider;
import ru.rolls.server.JwtAuthenticationFilter;
import ru.rolls.server.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	AuthProvider authProvider;

	@Autowired
	JwtAuthenticationFilter jwtAuthFilter;
	
	@Autowired
	LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(req -> 
			req.requestMatchers("/**")
			.permitAll()
			.requestMatchers("/work/**").hasAnyRole(ADMIN.name(), 
																DELIVERER.name(), 
																OPERATOR.name(), 
																CHEF.name())
			.requestMatchers("/app/**").hasRole("CLIENT")
			.anyRequest()
			.authenticated()
		)
		.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .authenticationProvider(authProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
		;

		return http.build();
	}


}
