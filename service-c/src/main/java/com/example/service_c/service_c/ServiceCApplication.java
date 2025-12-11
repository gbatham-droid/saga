package com.example.service_c.service_c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ServiceCApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCApplication.class, args);
	}

}
