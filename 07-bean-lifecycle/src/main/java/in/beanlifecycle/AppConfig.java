package in.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("in.beanlifecycle")
public class AppConfig {

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public AddToCart cartBean() {
//        return new AddToCart();
//    }
}
