package com.yash.product_rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductRatingApplication {

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	WebClient.Builder getWebClient(){
		return WebClient.builder();
	}


	public static void main(String[] args) {
		SpringApplication.run(ProductRatingApplication.class, args);
	}

}
