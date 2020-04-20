package com.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.sc"})

public class Application {

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}
	
}
