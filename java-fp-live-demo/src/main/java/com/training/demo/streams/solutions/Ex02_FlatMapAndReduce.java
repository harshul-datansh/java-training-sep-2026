package com.training.demo.streams.solutions;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;

import java.util.List;

/** SOLUTION to exercise 2. */
public class Ex02_FlatMapAndReduce {

    /** Why: flatMap turns "stream of lists" into "stream of SKUs"; distinct + sorted do the rest. */
    public static List<String> distinctSkusSorted(List<Order> orders) {
        return orders.stream()
                .flatMap(o -> o.skus().stream())
                .distinct()
                .sorted()
                .toList();
    }

    /**
     * Why: 0.0 is the identity for addition, so an empty stream gives 0.0.
     * (mapToDouble(Order::total).sum() is an equally good answer.)
     */
    public static double revenue(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.status() != OrderStatus.CANCELLED)
                .map(Order::total)
                .reduce(0.0, Double::sum);
    }
}
