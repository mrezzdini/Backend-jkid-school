package com.technoparent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class ParentApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}
}