package com.training.demo.functional.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex02_CustomDiscountRulesTest {

    private static final double CENT = 0.001;
    private final List<Order> orders = SampleData.orders();
    private final Order a100 = orders.get(0); // premium, 120.00
    private final Order a102 = orders.get(2); // premium, 560.00
    private final Order a106 = orders.get(6); // standard, 999.00
    private final Order a101 = orders.get(1); // standard, 35.50

    @Test
    void premiumTenPercent() {
        assertEquals(12.0, Ex02_CustomDiscountRules.premiumTenPercent().discountFor(a100), CENT);
        assertEquals(0.0, Ex02_CustomDiscountRules.premiumTenPercent().discountFor(a106), CENT);
    }

    @Test
    void flatOffAboveUsesBothParameters() {
        assertEquals(20.0, Ex02_CustomDiscountRules.flatOffAbove(500, 20).discountFor(a106), CENT);
        assertEquals(0.0, Ex02_CustomDiscountRules.flatOffAbove(500, 20).discountFor(a100), CENT);
        assertEquals(5.0, Ex02_CustomDiscountRules.flatOffAbove(120, 5).discountFor(a100), CENT, "boundary counts");
    }

    @Test
    void blackFridayAddsBothRules() {
        assertEquals(76.0, Ex02_CustomDiscountRules.blackFriday().discountFor(a102), CENT); // 56 + 20
        assertEquals(20.0, Ex02_CustomDiscountRules.blackFriday().discountFor(a106), CENT); // 0 + 20
        assertEquals(0.0, Ex02_CustomDiscountRules.blackFriday().discountFor(a101), CENT);
    }
}
