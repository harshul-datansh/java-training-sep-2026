package com.training.demo.lambdas.exercises;

import com.training.demo.model.Order;

import java.util.function.Predicate;

/**
 * EXERCISE 2 (easy/medium) - lambdas that capture a parameter.
 *
 * Support wants configurable filters: "orders of at least X EUR" and
 * "orders from this customer".
 *
 * Run the test:  mvn test -Dtest=Ex02_CaptureThresholdTest
 */
public class Ex02_CaptureThreshold {

    /**
     * Returns a Predicate that is true when the order total is greater than OR EQUAL to minimum.
     *
     * TODO: return a lambda that uses the minimum parameter.
     *
     * Hint: the lambda can read minimum because nobody changes it (Demo03).
     */
    public static Predicate<Order> totalAtLeast(double minimum) {
        return order -> order.total()>=minimum;
    }

    /**
     * Returns a Predicate that is true when the order was placed by the given email.
     * The comparison must ignore upper/lower case ("Anna@Shop.test" matches "anna@shop.test").
     *
     * TODO: return a lambda that uses the email parameter.
     *
     * Hint: String has a method for comparing text while ignoring case.
     */
    public static Predicate<Order> fromCustomer(String email) {
        return order -> order.customerEmail().equalsIgnoreCase(email);
    }
}
