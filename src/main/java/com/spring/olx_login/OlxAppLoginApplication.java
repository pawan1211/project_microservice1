package com.spring.olx_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OlxAppLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxAppLoginApplication.class, args);
	}

}
