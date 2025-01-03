package com.udaan.Kam;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KamApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(KamApplication.class, args);
	}

}
