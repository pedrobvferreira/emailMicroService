package com.ms.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MSRegistryApp {
	
	public static void main(String[] args) {
		SpringApplication.run(MSRegistryApp.class, args);
	}
}
