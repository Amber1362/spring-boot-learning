package in.beanscope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public OrderService order() {
        return new OrderService();
    }

    @Bean
    public OrderService order2() {
        return new OrderService();
    }
}
