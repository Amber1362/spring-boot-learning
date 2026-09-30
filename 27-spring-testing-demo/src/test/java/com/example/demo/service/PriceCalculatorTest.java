package com.example.demo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceCalculatorTest {

    private PriceCalculator priceCalculator = new PriceCalculator();

    //@Test
    void shouldApplyDiscountToPrice() {

        //arrange -> initial input
        double price = 1000;
        double discount = 20;

        //action -> action performed
        double actualPrice = priceCalculator.calculatorPrice(price, discount);

        //assertion -> expected output
        assertEquals(800, actualPrice);
    }

    @Test
    void shouldRejectDiscountAboveHundred() {

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> priceCalculator.calculatorPrice(
                                1000,
                                120
                        )
                );

        assertEquals("Discount should be within 0 to 100",
                exception.getMessage());
    }
}
