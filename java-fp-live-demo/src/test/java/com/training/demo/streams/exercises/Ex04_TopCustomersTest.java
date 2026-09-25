package com.training.demo.streams.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex04_TopCustomersTest {

    @Test
    void topThreeFromSampleData() {
        assertEquals(List.of("erik@shop.test", "carla@shop.test", "anna@shop.test"),
                Ex04_TopCustomers.topCustomers(SampleData.orders(), 3));
    }

    @Test
    void cancelledOrdersDoNotCount() {
        // dev only has a cancelled order, so dev must not appear at all
        assertEquals(List.of("erik@shop.test", "carla@shop.test", "anna@shop.test", "bob@shop.test"),
                Ex04_TopCustomers.topCustomers(SampleData.orders(), 10));
    }

    @Test
    void tiesAreBrokenAlphabetically() {
        List<Order> tie = List.of(
                new Order("T-1", "zoe@shop.test", false, 100, OrderStatus.PAID, List.of("X")),
                new Order("T-2", "amy@shop.test", false, 60, OrderStatus.PAID, List.of("X")),
                new Order("T-3", "amy@shop.test", false, 40, OrderStatus.NEW, List.of("X")));
        assertEquals(List.of("amy@shop.test", "zoe@shop.test"), Ex04_TopCustomers.topCustomers(tie, 2));
    }
}
