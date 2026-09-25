package com.training.demo.lambdas.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Ex02_CaptureThresholdTest {

    private final List<Order> orders = SampleData.orders();

    @Test
    void totalAtLeastIncludesTheBoundary() {
        Predicate<Order> atLeast120 = Ex02_CaptureThreshold.totalAtLeast(120);
        assertTrue(atLeast120.test(orders.get(0)), "A-100 has exactly 120.00");
        assertFalse(atLeast120.test(orders.get(1)), "A-101 has 35.50");
    }

    @Test
    void totalAtLeastRemembersItsOwnThreshold() {
        Predicate<Order> low = Ex02_CaptureThreshold.totalAtLeast(10);
        Predicate<Order> high = Ex02_CaptureThreshold.totalAtLeast(500);
        assertEquals(7, orders.stream().filter(low).count());
        assertEquals(2, orders.stream().filter(high).count());
    }

    @Test
    void fromCustomerIgnoresCase() {
        Predicate<Order> anna = Ex02_CaptureThreshold.fromCustomer("Anna@Shop.TEST");
        List<String> ids = orders.stream().filter(anna).map(Order::id).toList();
        assertEquals(List.of("A-100", "A-105"), ids);
    }
}
