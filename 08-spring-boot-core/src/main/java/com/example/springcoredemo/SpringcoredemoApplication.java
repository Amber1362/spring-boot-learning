package com.example.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);

//		PaymentGateway payment = context.getBean(PaymentGateway.class);
//		payment.setType("Google Pay");
//		payment.setRetryCount(5);

//		payment.print();
	}

}
