package com.vzurauskas.fortress.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.vzurauskas.fortress")
@EnableJpaRepositories("com.vzurauskas.fortress")
@EntityScan("com.vzurauskas.fortress")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
