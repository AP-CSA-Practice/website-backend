package com.apcsa.practice.controller;

import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Example REST Controller for basic API endpoints demonstration.
 * This controller provides simple endpoints for testing and learning purposes.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allows requests from the React frontend running on port 3000
public class ExampleController {
    
    /**
     * Returns the current server time as a formatted string.
     * 
     * @return A string containing the current date and time in the format "yyyy-MM-dd HH:mm:ss"
     */
    @GetMapping("/current-time")
    public String getCurrentTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Define the format pattern for the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // Format the date and time according to the pattern
        String formattedDateTime = now.format(formatter);
        
        // Return the formatted date and time with a descriptive message
        return "Current time is: " + formattedDateTime;
    }

}