package in.codedemo;

import in.codedemo.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    PaymentService payment;

    @Autowired
    OrderService(@Qualifier("upiPayment") PaymentService payment) {
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
