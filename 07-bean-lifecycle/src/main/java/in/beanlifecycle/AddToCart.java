package in.beanlifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AddToCart /*implements InitializingBean, DisposableBean*/ {

    Map<Integer, String> map;

    public AddToCart() {
        map = new HashMap<>();
        System.out.println("AddToCart Constructor called");
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("Initialization callback.");
//        map.put(1, "Amber");
//        map.put(2, "Amir");
//        map.put(3, "Aarav");
//    }

    public void start() {
        System.out.println("Initialization callback");
        map.put(1, "Amber");
        map.put(2, "Amir");
        map.put(3, "Aarav");
    }

    @PostConstruct
    public void start2() {
        System.out.println("Initialization callback");
        map.put(1, "Amber");
        map.put(2, "Amir");
        map.put(3, "Aarav");
    }


    public String getValue(int key) {
        return map.get(key);
    }

    @PreDestroy
    public void stop() {
        map.clear();
        System.out.println("The bean is getting destroyed");
    }

//    @Override
//    public void destroy() throws Exception {
//        map.clear();
//        System.out.println("The bean is getting destroyed");
//    }
}
