package com.itacademy.dices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DicesApplication.class, args);
	}

}
