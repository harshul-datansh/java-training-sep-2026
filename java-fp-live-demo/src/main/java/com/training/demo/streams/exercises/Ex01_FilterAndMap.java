package com.training.demo.streams.exercises;

import com.training.demo.model.Order;

import java.util.List;
import java.util.stream.Collectors;

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
        return orders.stream()
                .filter(Order::isPaid)
                .map(Order::id)
                .collect(Collectors.toList());

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
       return orders.stream()
               .filter(Order->Order.total()>=minTotal)
               .map(Order::customerEmail)
               .map(String::toUpperCase)
               .collect(Collectors.toList());

    }
}
