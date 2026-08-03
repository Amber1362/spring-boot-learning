package in.codedemo.notification;

public class PopupNotification implements NotificationService {

    @Override
    public void sentNotification() {
        System.out.println("PopUp notification sent.");
    }
}
