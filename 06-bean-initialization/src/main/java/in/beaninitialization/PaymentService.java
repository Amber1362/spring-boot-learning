package in.beaninitialization;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class PaymentService {

    private OrderService orderService;

    public PaymentService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void pay() {
        System.out.println("Payment done.");

        orderService.orderDetails();
    }
}
