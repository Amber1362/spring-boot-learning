package com.example.springcoredemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

//    @Value("${paymentGateway.type:Google Pay}")
//    private String type;
//
//    @Value("${paymentGateway.retryCount:10}")
//    private int retryCount;

//    public PaymentGateway( String type,
//                           int retryCount) {
//        this.type = type;
//        this.retryCount = retryCount;
//    }

    private PaymentProperties paymentProperties;

    public PaymentGateway(PaymentProperties paymentProperties) {
        this.paymentProperties = paymentProperties;
    }

    public PaymentProperties getPaymentProperties() {
        return paymentProperties;
    }

    public String getType() {
        return paymentProperties.getType();
    }

    public int getRetryCount() {
        return paymentProperties.getRetryCount();
    }

    public boolean isEnabled() {
        return paymentProperties.isEnabled();
    }

    public int getTimeout() {
        return paymentProperties.getTimeOut();
    }

    public void print() {
        System.out.println(getType());
        System.out.println(getRetryCount());
        System.out.println(isEnabled());
        System.out.println(getTimeout());
    }
}
