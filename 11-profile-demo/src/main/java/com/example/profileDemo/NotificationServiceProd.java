package com.example.profileDemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class NotificationServiceProd implements NotificationService{

    @Override
    public String send() {
        return "PRODUCTION: Here is a notification";
    }
}
