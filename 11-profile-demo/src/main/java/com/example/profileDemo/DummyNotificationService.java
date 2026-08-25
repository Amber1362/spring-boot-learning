package com.example.profileDemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DummyNotificationService implements NotificationService{

    @Override
    public String send() {
        return "DEVELOPMENT: Here is a notification";
    }
}
