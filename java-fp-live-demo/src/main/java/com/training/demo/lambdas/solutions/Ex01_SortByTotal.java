package com.training.demo.lambdas.solutions;

import com.training.demo.model.Order;

import java.util.Comparator;

/** SOLUTION to exercise 1. */
public class Ex01_SortByTotal {

    /**
     * Why: swapping b and a inverts the natural order, so the biggest total wins.
     * (Also fine: Comparator.comparingDouble(Order::total).reversed())
     */
    public static Comparator<Order> byTotalDescending() {
        return (a, b) -> Double.compare(b.total(), a.total());
    }
}
