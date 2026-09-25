package com.training.demo.streams.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex03_CollectorsTest {

    private final List<Order> orders = SampleData.orders();

    @Test
    void countByStatus() {
        Map<OrderStatus, Long> expected = Map.of(
                OrderStatus.NEW, 2L,
                OrderStatus.PAID, 3L,
                OrderStatus.SHIPPED, 1L,
                OrderStatus.CANCELLED, 1L);
        assertEquals(expected, Ex03_Collectors.countByStatus(orders));
    }

    @Test
    void spendByCustomerMergesRepeatCustomers() {
        Map<String, Double> spend = Ex03_Collectors.spendByCustomer(orders);
        assertEquals(5, spend.size());
        assertEquals(360.0, spend.get("anna@shop.test"), 0.001);
        assertEquals(125.49, spend.get("bob@shop.test"), 0.001);
        assertEquals(15.0, spend.get("dev@shop.test"), 0.001);
    }

    @Test
    void premiumNewsletterLine() {
        assertEquals("anna@shop.test; carla@shop.test", Ex03_Collectors.premiumNewsletterLine(orders));
    }
}
