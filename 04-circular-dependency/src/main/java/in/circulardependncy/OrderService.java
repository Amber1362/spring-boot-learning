package in.circulardependncy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    PaymentService paymentService;

//    public OrderService() {
//        System.out.println("OrderService created");
//        this.paymentService = new PaymentService();
//    }

    public void orderPlaced() {
        paymentService.pay();
        System.out.println("Order placed!");
    }

    public void orderDetails() {
        System.out.println("Order details...");
    }
}
