package in.codedemo;

import in.codedemo.notification.EmailNotification;
import in.codedemo.notification.NotificationService;
import in.codedemo.notification.PopupNotification;
import in.codedemo.notification.SmsNotification;

import javax.management.Notification;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        NotificationService notification = new SmsNotification();
        OrderService order = new OrderService();
        order.setNotification(notification);
        order.orderPlaced();
    }
}
