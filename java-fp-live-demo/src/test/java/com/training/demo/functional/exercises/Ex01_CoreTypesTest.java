package com.training.demo.functional.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Ex01_CoreTypesTest {

    private final List<Order> orders = SampleData.orders();
    private final Order a100 = orders.get(0); // premium, 2 items, 120.00
    private final Order a101 = orders.get(1); // standard, 1 item, 35.50
    private final Order a103 = orders.get(3); // standard, 3 items, 89.99

    @Test
    void isPremium() {
        assertTrue(Ex01_CoreTypes.isPremium().test(a100));
        assertFalse(Ex01_CoreTypes.isPremium().test(a101));
    }

    @Test
    void itemCount() {
        assertEquals(2, Ex01_CoreTypes.itemCount().apply(a100));
        assertEquals(3, Ex01_CoreTypes.itemCount().apply(a103));
    }

    @Test
    void containsSku() {
        assertTrue(Ex01_CoreTypes.containsSku().test(a103, "BOOK-7"));
        assertFalse(Ex01_CoreTypes.containsSku().test(a100, "BOOK-7"));
    }

    @Test
    void biggerOrderPicksLargerTotalAndFirstOnTie() {
        assertSame(a100, Ex01_CoreTypes.biggerOrder().apply(a100, a101));
        assertSame(a100, Ex01_CoreTypes.biggerOrder().apply(a101, a100));
        Order twin = new Order("A-999", "x@shop.test", false, 120.00, a100.status(), List.of());
        assertSame(twin, Ex01_CoreTypes.biggerOrder().apply(twin, a100), "tie -> first argument");
    }
}
