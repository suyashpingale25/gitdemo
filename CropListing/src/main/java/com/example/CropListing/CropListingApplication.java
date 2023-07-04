package com.example.CropListing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CropListingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropListingApplication.class, args);
	}

}

