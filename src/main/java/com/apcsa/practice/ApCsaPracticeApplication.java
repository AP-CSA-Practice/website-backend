package com.apcsa.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the AP Computer Science A Practice platform.
 * This is the entry point for the Spring Boot application that provides
 * a backend service for AP CSA practice questions and related functionality.
 * 
 * The application uses Spring Boot's auto-configuration and component scanning
 * to set up the necessary beans and services.
 */
@SpringBootApplication
public class ApCsaPracticeApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 * 
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApCsaPracticeApplication.class, args);
	}

}
