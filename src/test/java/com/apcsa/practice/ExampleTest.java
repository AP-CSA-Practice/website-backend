package com.apcsa.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Example test class demonstrating basic JUnit testing with Spring Boot.
 * This class contains simple tests to verify the application context loads correctly
 * and to demonstrate basic JUnit assertion functionality.
 */
@SpringBootTest
class ExampleTest {

    /**
     * The Spring application context, automatically injected by Spring Boot.
     * Used to verify that the application context loads successfully.
     */
	@Autowired
	private ApplicationContext applicationContext;

    /**
     * Tests that the Spring application context loads successfully.
     * This is a basic sanity check to ensure the application can start.
     */
	@Test
	void contextLoads() {
        // Test that the application context loads successfully
		assertNotNull(applicationContext, "Application context should not be null");
	}

    /**
     * Demonstrates basic string comparison and manipulation tests.
     * This test shows how to:
     * - Compare strings for equality
     * - Check if a string contains a substring
     * - Verify string length
     */
	@Test
	void simpleStringTest() {
        // A simple string comparison test
		String expected = "Hello, AP CSA Practice!";
		String actual = "Hello, AP CSA Practice!";
		
		assertEquals(expected, actual, "Strings should be equal");
		
        // Test string containment
		String message = "Welcome to AP Computer Science A Practice";
		assertTrue(message.contains("AP Computer Science"), "Message should contain 'AP Computer Science'");
		
        // Test string length
		assertTrue(message.length() > 10, "Message length should be greater than 10");
	}

    /**
     * Demonstrates basic arithmetic operation tests.
     * This test shows how to verify:
     * - Integer addition
     * - Floating-point division (with delta for precision)
     * - Modulo operation
     */
	@Test
	void simpleCalculationTest() {
        // Simple addition test
		int result = 5 + 3;
		assertEquals(8, result, "5 + 3 should equal 8");

        // Test division with floating-point numbers
		double divisionResult = 10.0 / 2.0;
		assertEquals(5.0, divisionResult, 0.001, "10.0 / 2.0 should equal 5.0");
		
        // Test remainder operation
		int remainder = 10 % 3;
		assertEquals(1, remainder, "10 % 3 should equal 1");
	}
}