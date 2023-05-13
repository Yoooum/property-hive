package com.prprv.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PropertyHiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyHiveApplication.class, args);
	}

}
