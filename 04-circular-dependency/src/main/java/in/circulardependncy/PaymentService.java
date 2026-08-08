package in.circulardependncy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    @Autowired
    OrderService orderService;

//    public PaymentService() {
//        System.out.println("PaymentService created");
//        this.orderService = new OrderService();
//    }

    public void pay() {
        System.out.println("Payment received!");
        orderService.orderDetails();
    }
}
