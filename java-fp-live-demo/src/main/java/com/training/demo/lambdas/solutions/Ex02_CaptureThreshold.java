package com.training.demo.lambdas.solutions;

import com.training.demo.model.Order;

import java.util.function.Predicate;

/** SOLUTION to exercise 2. */
public class Ex02_CaptureThreshold {

    /** Why: minimum is effectively final, so the lambda may capture (copy) it. */
    public static Predicate<Order> totalAtLeast(double minimum) {
        return o -> o.total() >= minimum;
    }

    /** Why: same capture idea; equalsIgnoreCase handles the upper/lower case rule. */
    public static Predicate<Order> fromCustomer(String email) {
        return o -> o.customerEmail().equalsIgnoreCase(email);
    }
}
