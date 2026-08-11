package in.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("Order")
public class OrderService implements BeanNameAware, ApplicationContextAware {

    PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        System.out.println("OrderService created");
        this.paymentService = paymentService;
    }

    public void orderPlaced() {
        paymentService.pay();
        System.out.println("Order placed!");
        orderDetails();
    }

    public void orderDetails() {
        System.out.println("Order details...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name: " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Appication Context: " + applicationContext);
    }
}
