package com.rishikesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringRedditBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedditBackendApplication.class, args);
	}

}
