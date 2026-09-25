package com.training.demo.streams.exercises;

import com.training.demo.model.Order;

import java.util.List;

/**
 * EXERCISE 1 (easy) - your first pipelines: filter + map + collect.
 *
 * Run the test:  mvn test -Dtest=Ex01_FilterAndMapTest
 */
public class Ex01_FilterAndMap {

    /**
     * The ids of all PAID orders, in the same order as the input.
     *
     * TODO: write a stream pipeline.
     *
     * Hint: source -> keep some -> turn each into something else -> collect.
     * Order has a helper method that tells you whether it is paid.
     */
    public static List<String> paidOrderIds(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * The customer emails of all orders with a total of at least minTotal,
     * in UPPER CASE, in the same order as the input (duplicates are fine here).
     *
     * TODO: write a stream pipeline.
     *
     * Hint: you may use map more than once in the same pipeline.
     */
    public static List<String> bigSpenderEmails(List<Order> orders, double minTotal) {
        throw new UnsupportedOperationException("TODO");
    }
}
