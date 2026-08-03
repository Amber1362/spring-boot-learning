package in.codedemo.notification;

public class EmailNotification implements NotificationService {

    @Override
    public void sentNotification() {
        System.out.println("Email notification sent.");
    }
}
