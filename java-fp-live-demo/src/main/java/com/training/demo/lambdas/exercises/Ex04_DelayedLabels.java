package com.training.demo.lambdas.exercises;

import com.training.demo.model.Order;

import java.util.List;
import java.util.function.Supplier;

/**
 * EXERCISE 4 (hard) - the loop-variable trap, for real.
 *
 * The warehouse prints shipping labels in a background job that runs LATER.
 * We hand it one Supplier per order; each Supplier produces the label text.
 *
 * Run the test:  mvn test -Dtest=Ex04_DelayedLabelsTest
 */
public class Ex04_DelayedLabels {

    /**
     * Returns one label Supplier per order, in the same order as the input.
     * Label format: "position/count id", with position starting at 1.
     * Example for 3 orders: "1/3 A-100", "2/3 A-101", "3/3 A-102".
     *
     * The Suppliers are called AFTER this method has returned.
     *
     * TODO: build and return the list of Suppliers.
     *
     * Hint: you need the index, so a classic for loop is natural - but the
     * compiler will not let a lambda use a variable that the loop keeps changing.
     * Demo04 shows the way out.
     */
    public static List<Supplier<String>> labelJobs(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }
}
