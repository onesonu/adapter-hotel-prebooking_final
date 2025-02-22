package com.fastays.adapter_hotel_prebooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.fastays.adapter_hotel_prebooking.repository")
public class AdapterHotelPrebookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdapterHotelPrebookingApplication.class, args);
	}

}
