package com.cognixia.studentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Driver Class
 * 
 * To test HTTP Post, Put, and Delete
 * you can either use HTTP Client that comes with IntelliJ
 * Or you can use Postman if using an IDE like Eclipse
 * 
 * @author fredrick ottensmeyer
 */

@SpringBootApplication
public class StudentmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagerApplication.class, args);
	}

}
