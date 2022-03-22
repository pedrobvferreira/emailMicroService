package com.ms.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Pedro Ferreira
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class MSUserApp {
	
	public static void main(String[] args) {
		SpringApplication.run(MSUserApp.class, args);
	}
}
