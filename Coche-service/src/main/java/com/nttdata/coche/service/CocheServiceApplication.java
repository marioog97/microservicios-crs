package com.nttdata.coche.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CocheServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocheServiceApplication.class, args);
	}

}
