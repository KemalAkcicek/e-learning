package com.kemalakcicek.e_learning.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = { "com.kemalakcicek" })
@EnableJpaRepositories(basePackages = { "com.kemalakcicek" })
@ComponentScan(basePackages = { "com.kemalakcicek" })
@SpringBootApplication
public class ELearningStarter {

	public static void main(String[] args) {
		SpringApplication.run(ELearningStarter.class, args);
	}

}
