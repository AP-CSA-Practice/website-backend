package com.apcsa.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Configuration Class
 * This class configures the security settings for the application,
 * defining access rules for different endpoints and security features.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     * 
     * @param http The HttpSecurity object to configure
     * @return The built SecurityFilterChain
     * @throws Exception If an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/**").permitAll()  // Allow unrestricted access to all /api/** paths
                .anyRequest().authenticated()            // Require authentication for any other request
            )
            .csrf(csrf -> csrf.disable());  // Temporarily disable CSRF protection for development purposes
        return http.build();
    }
}
