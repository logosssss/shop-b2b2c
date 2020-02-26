package com.cloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableRabbit
@EnableEurekaClient
public class message {
	public static void main(String[] args) {
		SpringApplication.run(message.class, args);
	}
}
