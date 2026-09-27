package com.training.demo.functional.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;

import java.util.function.Predicate;

/**
 * EXERCISE 3 (medium) - combine small predicates instead of writing big ones.
 *
 * Use ONLY the building blocks below plus the helper methods
 * and, or, negate and Predicate.not. Do not write new lambdas.
 *
 * Run the test:  mvn test -Dtest=Ex03_CombinePredicatesTest
 */
public class Ex03_CombinePredicates {

    public static final Predicate<Order> IS_PAID = o -> o.status() == OrderStatus.PAID;
    public static final Predicate<Order> IS_SHIPPED = o -> o.status() == OrderStatus.SHIPPED;
    public static final Predicate<Order> IS_PREMIUM = Order::premium;
    public static final Predicate<Order> IS_BIG = o -> o.total() >= 500;
    public static final Predicate<Order> IS_CANCELLED = o -> o.status() == OrderStatus.CANCELLED;

    /**
     * Paid AND big.
     *
     * TODO: combine two building blocks.
     *
     * Hint: Predicate has a default method named after the word in capitals above.
     */
    public static Predicate<Order> paidAndBig() {
        return  IS_BIG.and(IS_PAID);
    }

    /**
     * Needs manual fraud review: a big order from a customer who is NOT premium.
     *
     * TODO: combine building blocks.
     *
     * Hint: "not premium" is IS_PREMIUM turned upside down.
     */
    public static Predicate<Order> needsReview() {
        return IS_BIG.and(IS_PREMIUM.negate());
    }

    /**
     * VIP handling: the customer is premium OR the order is big (or both).
     *
     * TODO: combine two building blocks.
     *
     * Hint: same idea as paidAndBig, different word.
     */
    public static Predicate<Order> premiumOrBig() {
       return IS_PREMIUM.or(IS_BIG);
    }

    /**
     * Still open: the order is NOT cancelled and NOT shipped.
     *
     * TODO: combine building blocks.
     *
     * Hint: try the STATIC helper on Predicate this time - it reads nicely
     * when you say "not cancelled" out loud.
     */
    public static Predicate<Order> stillOpen() {
        return IS_CANCELLED.negate().and(IS_SHIPPED.negate());
    }
}
