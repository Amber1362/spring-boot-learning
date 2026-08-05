package in.codedemo;

import in.springcoredemo.CartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService order = context.getBean(OrderService.class);
        order.orderPlaced();

//        PaymentService payment = context.getBean(PaymentService.class);
//        payment.paymentDone();

        User user = context.getBean(User.class);
        System.out.println(user.getName());

        CartService cs = context.getBean(CartService.class);
        cs.addToCart();
    }
}
