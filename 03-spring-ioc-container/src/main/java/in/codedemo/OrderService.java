package in.codedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    PaymentService payment;

    @Autowired
    OrderService(PaymentService payment) {
        this.payment = payment;
    }


//    @Autowired
//    public void setPayment(PaymentService payment) {
//        this.payment = payment;
//    }

    public void orderPlaced() {
        payment.paymentDone();
        System.out.println("Order placed!");
    }
}
