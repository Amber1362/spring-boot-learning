package in.codedemo;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    public void paymentDone() {
        System.out.println("Payment recieved!");
    }
}
