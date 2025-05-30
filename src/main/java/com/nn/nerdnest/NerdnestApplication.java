package com.nn.nerdnest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = "com.nn.nerdnest")
public class NerdnestApplication {
	public static void main(String[] args) {
		SpringApplication.run(NerdnestApplication.class, args);
	}

}