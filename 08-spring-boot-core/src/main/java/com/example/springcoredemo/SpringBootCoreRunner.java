package com.example.springcoredemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringBootCoreRunner implements ApplicationRunner {

    private PaymentGateway paymentGateway;

    public SpringBootCoreRunner(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        paymentGateway.print();
    }
}
