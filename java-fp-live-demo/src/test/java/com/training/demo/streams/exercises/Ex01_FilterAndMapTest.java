package com.training.demo.streams.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex01_FilterAndMapTest {

    private final List<Order> orders = SampleData.orders();

    @Test
    void paidOrderIdsKeepsInputOrder() {
        assertEquals(List.of("A-100", "A-103", "A-105"), Ex01_FilterAndMap.paidOrderIds(orders));
    }

    @Test
    void paidOrderIdsOfEmptyListIsEmpty() {
        assertEquals(List.of(), Ex01_FilterAndMap.paidOrderIds(List.of()));
    }

    @Test
    void bigSpenderEmailsAreUpperCaseAndIncludeBoundary() {
        assertEquals(List.of("ANNA@SHOP.TEST", "CARLA@SHOP.TEST", "ANNA@SHOP.TEST", "ERIK@SHOP.TEST"),
                Ex01_FilterAndMap.bigSpenderEmails(orders, 120));
    }
}
