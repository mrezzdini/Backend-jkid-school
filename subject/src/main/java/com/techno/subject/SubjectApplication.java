package com.techno.subject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubjectApplication.class, args);
	}
}