package com.training.demo.streams.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex02_FlatMapAndReduceTest {

    private final List<Order> orders = SampleData.orders();

    @Test
    void distinctSkusSorted() {
        assertEquals(List.of("BAG-3", "BOOK-1", "BOOK-7", "HEADSET-5", "LAPTOP-9", "MUG-4", "PEN-2"),
                Ex02_FlatMapAndReduce.distinctSkusSorted(orders));
    }

    @Test
    void revenueExcludesCancelled() {
        // all totals 2059.49 minus the cancelled A-104 (15.00)
        assertEquals(2044.49, Ex02_FlatMapAndReduce.revenue(orders), 0.001);
    }

    @Test
    void revenueOfEmptyListIsZero() {
        assertEquals(0.0, Ex02_FlatMapAndReduce.revenue(List.of()), 0.001);
    }
}
