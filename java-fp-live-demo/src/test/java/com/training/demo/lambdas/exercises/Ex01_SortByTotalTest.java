package com.training.demo.lambdas.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex01_SortByTotalTest {

    @Test
    void sortsMostExpensiveFirst() {
        List<Order> orders = new ArrayList<>(SampleData.orders());

        orders.sort(Ex01_SortByTotal.byTotalDescending());

        List<String> ids = orders.stream().map(Order::id).toList();
        assertEquals(List.of("A-106", "A-102", "A-105", "A-100", "A-103", "A-101", "A-104"), ids);
    }

    @Test
    void equalTotalsCompareAsZero() {
        Order a = SampleData.orders().get(0);
        assertEquals(0, Ex01_SortByTotal.byTotalDescending().compare(a, a));
    }
}
