package com.training.demo.lambdas.exercises;

import com.training.demo.model.Order;

import java.util.Comparator;

/**
 * EXERCISE 1 (easy) - your first lambda.
 *
 * The dashboard wants the MOST EXPENSIVE orders at the top.
 *
 * Run the test:  mvn test -Dtest=Ex01_SortByTotalTest
 */
public class Ex01_SortByTotal {

    /**
     * Returns a Comparator that sorts orders by total, highest total first.
     *
     * TODO: replace the throw with a lambda of the form (a, b) -> ...
     *
     * Hint: a Comparator returns a negative number when a should come first.
     * Look at how Demo01 sorted cheapest-first, then think about what to swap.
     */
    public static Comparator<Order> byTotalDescending() {
        throw new UnsupportedOperationException("TODO");
    }
}
