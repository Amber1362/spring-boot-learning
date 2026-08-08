package in.beanscope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;

public class Main {
    static void main() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        OrderService order = context.getBean(OrderService.class);
//        OrderService order2 = context.getBean(OrderService.class);
//
//        OrderService order3 = new OrderService();

//        System.out.println(order == order2);

    }
}
