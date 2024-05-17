package com.hackaton.bagginscoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BagginscoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BagginscoffeeApplication.class, args);
	}
}
