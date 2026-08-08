package in.beaninitialization;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private PaymentService paymentService;

    public OrderService(@Lazy PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void orderPlaced() {
        paymentService.pay();
        System.out.println("Order placed.");
    }

    public void orderDetails() {
        System.out.println("Order details...");
    }
}
