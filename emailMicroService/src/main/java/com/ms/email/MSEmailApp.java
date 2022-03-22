package com.ms.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Pedro Ferreira
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class MSEmailApp {
	
	public static void main(String[] args) {
		SpringApplication.run(MSEmailApp.class, args);
	}
}
