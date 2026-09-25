package com.training.demo.streams.exercises;

import com.training.demo.model.Order;

import java.util.List;

/**
 * EXERCISE 4 (hard) - end to end: a "top customers" report.
 *
 * Run the test:  mvn test -Dtest=Ex04_TopCustomersTest
 */
public class Ex04_TopCustomers {

    /**
     * The emails of the n customers who spent the most, biggest spender first.
     *   - cancelled orders do not count
     *   - a customer's spend is the SUM of their orders
     *   - if two customers spent the same, the alphabetically smaller email comes first
     *   - if there are fewer than n customers, return all of them
     *
     * Expected for the sample data with n = 3: [erik@shop.test, carla@shop.test, anna@shop.test]
     *
     * TODO: build it with streams.
     *
     * Hint: think in two steps. Step 1 builds a Map from email to total spend
     * (Exercise 3 did something very similar). Step 2 streams over that map's
     * entries, sorts them, and keeps only the first n. Map.Entry has ready-made
     * comparators you can chain with thenComparing.
     */
    public static List<String> topCustomers(List<Order> orders, int n) {
        throw new UnsupportedOperationException("TODO");
    }
}
