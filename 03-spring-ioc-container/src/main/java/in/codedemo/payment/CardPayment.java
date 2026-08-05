package in.codedemo.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier
public class CardPayment implements PaymentService {

    @Override
    public void paymentDone() {
        System.out.println("Pay via card");
    }
}
