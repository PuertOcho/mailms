package com.mailms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.mailms"})
@SpringBootApplication
public class MailmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailmsApplication.class, args);
	}

}
