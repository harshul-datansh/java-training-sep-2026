package com.training.demo.streams.solutions;

import com.training.demo.model.Order;

import java.util.List;

/** SOLUTION to exercise 1. */
public class Ex01_FilterAndMap {

    /** Why: filter keeps paid orders, map turns each Order into its id; streams keep encounter order. */
    public static List<String> paidOrderIds(List<Order> orders) {
        return orders.stream()
                .filter(Order::isPaid)
                .map(Order::id)
                .toList();
    }

    /** Why: each map is one small step (Order -> email -> upper-case email), easy to read. */
    public static List<String> bigSpenderEmails(List<Order> orders, double minTotal) {
        return orders.stream()
                .filter(o -> o.total() >= minTotal)
                .map(Order::customerEmail)
                .map(String::toUpperCase)
                .toList();
    }
}
