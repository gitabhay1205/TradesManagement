package com.config.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradesMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradesMangementApplication.class, args);
	}

}
