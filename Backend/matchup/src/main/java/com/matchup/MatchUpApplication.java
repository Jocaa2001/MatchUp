package com.matchup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MatchUpApplication {

	static void main(String[] args) {
		SpringApplication.run(MatchUpApplication.class, args);
		System.out.println("Application started");
	}

}
