package com.training.demo.streams.solutions;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** SOLUTION to exercise 4. */
public class Ex04_TopCustomers {

    /**
     * Why: grouping first gives one number per customer; then sorting the map entries
     * (by value descending, then key ascending for ties) and limit(n) gives the top n.
     */
    public static List<String> topCustomers(List<Order> orders, int n) {
        Map<String, Double> spend = orders.stream()
                .filter(o -> o.status() != OrderStatus.CANCELLED)
                .collect(Collectors.groupingBy(Order::customerEmail, Collectors.summingDouble(Order::total)));

        Comparator<Map.Entry<String, Double>> bySpendDesc =
                Map.Entry.<String, Double>comparingByValue().reversed();

        return spend.entrySet().stream()
                .sorted(bySpendDesc.thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }
}
