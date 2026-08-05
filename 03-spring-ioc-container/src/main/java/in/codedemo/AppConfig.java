package in.codedemo;

import in.codedemo.payment.CardPayment;
import in.codedemo.payment.PaymentService;
import in.codedemo.payment.UpiPayment;
import in.springcoredemo.CartService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("in.codedemo")
public class AppConfig {

    @Bean
     public User createUser() {
         return new User("Amber", 19);
     }

     @Bean
    public CartService createCartService() {
        return new CartService();
     }

     @Bean
//     @Primary
     @Qualifier("cp")
     public PaymentService createCardPaymentService() {
        return new CardPayment();
     }

     @Bean
//     @Primary
     @Qualifier("up")
     public PaymentService createUpiPaymentService() {
        return new UpiPayment();
     }

     @Bean
    public OrderService createOrderService(@Qualifier("up") PaymentService paymentService) {
        return new OrderService( paymentService);
     }
}
