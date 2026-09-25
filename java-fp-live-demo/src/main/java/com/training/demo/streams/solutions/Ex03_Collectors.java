package com.training.demo.streams.solutions;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** SOLUTION to exercise 3. */
public class Ex03_Collectors {

    /** Why: groupingBy makes the groups; counting() turns each group into its size. */
    public static Map<OrderStatus, Long> countByStatus(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::status, Collectors.counting()));
    }

    /**
     * Why: the third toMap argument merges values when a key repeats (bob has two orders).
     * (groupingBy(Order::customerEmail, summingDouble(Order::total)) also works.)
     */
    public static Map<String, Double> spendByCustomer(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.toMap(Order::customerEmail, Order::total, Double::sum));
    }

    /** Why: distinct removes anna's second order, sorted gives alphabetical, joining glues. */
    public static String premiumNewsletterLine(List<Order> orders) {
        return orders.stream()
                .filter(Order::premium)
                .map(Order::customerEmail)
                .distinct()
                .sorted()
                .collect(Collectors.joining("; "));
    }
}
