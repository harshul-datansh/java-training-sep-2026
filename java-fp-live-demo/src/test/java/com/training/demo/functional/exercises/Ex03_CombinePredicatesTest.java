package com.training.demo.functional.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Ex03_CombinePredicatesTest {

    private final List<Order> orders = SampleData.orders();

    private List<String> ids(Predicate<Order> p) {
        return orders.stream().filter(p).map(Order::id).toList();
    }

    @Test
    void paidAndBig() {
        assertEquals(List.of(), ids(Ex03_CombinePredicates.paidAndBig()), "no paid order in the sample is >= 500");
        Order bigPaid = new Order("X-1", "x@shop.test", false, 700, OrderStatus.PAID, List.of("TV-1"));
        assertTrue(Ex03_CombinePredicates.paidAndBig().test(bigPaid));
    }

    @Test
    void needsReview() {
        assertEquals(List.of("A-106"), ids(Ex03_CombinePredicates.needsReview()));
    }

    @Test
    void premiumOrBig() {
        assertEquals(List.of("A-100", "A-102", "A-105", "A-106"), ids(Ex03_CombinePredicates.premiumOrBig()));
    }

    @Test
    void stillOpen() {
        assertEquals(List.of("A-100", "A-101", "A-103", "A-105", "A-106"), ids(Ex03_CombinePredicates.stillOpen()));
    }
}
