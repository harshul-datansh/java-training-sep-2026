package com.training.demo.streams.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * EXERCISE 3 (medium) - Collectors: groupingBy, toMap, joining.
 *
 * Run the test:  mvn test -Dtest=Ex03_CollectorsTest
 */
public class Ex03_Collectors {

    /**
     * How many orders there are for each status, e.g. {PAID=3, NEW=2, ...}.
     * Statuses without orders do not appear in the map.
     *
     * TODO: collect with a Collector from java.util.stream.Collectors.
     *
     * Hint: groupingBy accepts a second argument that says what to do with each group.
     */
    public static Map<OrderStatus, Long> countByStatus(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::status,Collectors.counting()));
    }

    /**
     * Total spent per customer email (all statuses count), e.g. {bob@shop.test=125.49, ...}.
     * Some customers have more than one order.
     *
     * TODO: collect into a Map.
     *
     * Hint: if you use toMap, remember what Demo05 showed about duplicate keys.
     */
    public static Map<String, Double> spendByCustomer(List<Order> orders) {
       return orders.stream()
                .collect(Collectors.toMap(Order::customerEmail, Order::total, Double::sum, TreeMap::new));
    }

    /**
     * The premium customers' emails as ONE line for the newsletter:
     * distinct, alphabetical, separated by "; ".
     * Example: "anna@shop.test; carla@shop.test"
     *
     * TODO: write a stream pipeline that ends in a joining collector.
     *
     * Hint: anna has two orders but must appear only once.
     */
    public static String premiumNewsletterLine(List<Order> orders) {
      return   orders.stream()
                .filter(Order::premium)
                .map(Order::customerEmail)
                .distinct()
                .collect(Collectors.joining("; "));
    }
}
