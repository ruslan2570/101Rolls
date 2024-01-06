package ru.rolls.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static ru.rolls.server.entity.EmployeeRole.ADMIN;
import static ru.rolls.server.entity.EmployeeRole.CHEF;
import static ru.rolls.server.entity.EmployeeRole.DELIVERER;
import static ru.rolls.server.entity.EmployeeRole.OPERATOR;

import ru.rolls.server.AuthProvider;
import ru.rolls.server.JwtAuthenticationFilter;

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
