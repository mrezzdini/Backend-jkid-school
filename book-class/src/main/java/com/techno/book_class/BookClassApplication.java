package com.techno.book_class;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookClassApplication.class, args);
	}
}