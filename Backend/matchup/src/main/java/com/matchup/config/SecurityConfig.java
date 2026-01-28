package com.matchup.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())          // onemogući CSRF da POST radi iz Postmana
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()          // dozvoli sve endpoint-e
                );

        return http.build();
    }

}
