package com.cognixia.studentmanager.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author fredrick ottensmeyer
 */

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository repository) {
		// This will create 2 new students for our database
		return args -> {
			Student donald = new Student(
					"Donald",
					"donald.duck@gmail.com",
					LocalDate.of(2000, Month.JANUARY, 5)
			);
			
			Student alex = new Student(
					"Alex",
					"alex@gmail.com",
					LocalDate.of(2004, Month.JANUARY, 5)
			);
			// This will save these two students in our database
			repository.saveAll(
					List.of(donald, alex)
			); 
		};
	}
	
}
