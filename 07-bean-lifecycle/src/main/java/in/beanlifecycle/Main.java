package in.beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AddToCart cart = context.getBean(AddToCart.class);
        System.out.println(cart.getValue(3));

        context.close();

//        OrderService order = context.getBean(OrderService.class);
//        order.orderPlaced();
    }
}
