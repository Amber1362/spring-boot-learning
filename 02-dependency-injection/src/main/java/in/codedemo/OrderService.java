package in.codedemo;

import in.codedemo.notification.NotificationService;

public class OrderService {

    NotificationService notification;

//    public OrderService(NotificationService notification) {
//        this.notification = notification;
//    }
//
//    public OrderService() {
//
//    }

    public void orderPlaced() {
        System.out.println("Order placed!");
        notification.sentNotification();
    }

    public void setNotification(NotificationService notification) {
        this.notification = notification;
    }
}
