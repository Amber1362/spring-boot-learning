package in.codedemo.notification;

public class SmsNotification implements NotificationService {

    @Override
    public void sentNotification() {
        System.out.println("SMS notification sent.");
    }
}
