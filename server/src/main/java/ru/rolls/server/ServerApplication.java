package ru.rolls.server;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.rolls.server.entity.Employee;
import ru.rolls.server.entity.EmployeeRole;
import ru.rolls.server.entity.Restaurant;
import ru.rolls.server.repo.ClientRepo;
import ru.rolls.server.repo.EmployeeRepo;
import ru.rolls.server.repo.RestaurantRepo;
import ru.rolls.server.service.AuthenticationService;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			EmployeeRepo employeeRepo, ClientRepo clientRepo, RestaurantRepo restaurantRepo,
			PasswordEncoder passwordEncoder) {
		return args -> {

			Restaurant restaurant = Restaurant
					.builder()
					.name("Ресторан центральный | Офис")
					.openingTime(LocalTime.of(8, 0, 0, 0))
					.closingTime(LocalTime.of(8, 0, 0, 0))
					.maxDistance(10)
					.minOrderSum(new BigDecimal(1000))
					.isClosed(false)
					.isTemporaryClosed(false)
					.build();

			restaurantRepo.save(restaurant);

			List<Employee> employees = new ArrayList<>();

			Employee admin = Employee.builder().lastname("Админов").firstname("Админ").patronymic("Админович")
					.login("admin").password(passwordEncoder.encode("admin")).role(EmployeeRole.ADMIN)
					.restaurant(restaurant).build();

			Employee deliverer = Employee.builder().lastname("Курьеров").firstname("Курьер").patronymic("Курьерович")
					.login("deliverer").password(passwordEncoder.encode("deliverer")).role(EmployeeRole.DELIVERER)
					.restaurant(restaurant)
					.build();

			Employee operator = Employee.builder().lastname("Операторов").firstname("Оператор")
					.patronymic("Операторович")
					.login("operator").password(passwordEncoder.encode("operator")).role(EmployeeRole.OPERATOR)
					.restaurant(restaurant).build();

			Employee chef = Employee.builder().lastname("Шефов").firstname("Шеф").patronymic(null)
					.login("chef").password(passwordEncoder.encode("chef")).role(EmployeeRole.CHEF)
					.restaurant(restaurant).build();

			employees.add(admin);
			employees.add(deliverer);
			employees.add(operator);
			employees.add(chef);

			employeeRepo.saveAll(employees);

		};
	}

}
