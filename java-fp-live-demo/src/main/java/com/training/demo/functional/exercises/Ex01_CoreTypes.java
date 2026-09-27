package com.training.demo.functional.exercises;

import com.training.demo.model.Order;

import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * EXERCISE 1 (easy) - write a lambda for each core type.
 *
 * The return type tells you the shape: what goes in, what comes out.
 *
 * Run the test:  mvn test -Dtest=Ex01_CoreTypesTest
 */
public class Ex01_CoreTypes {

    /**
     * Predicate: true when the order belongs to a premium customer.
     *
     * TODO: return a lambda (or method reference).
     *
     * Hint: Predicate means "Order in, boolean out". Which field of Order is a boolean?
     */
    public static Predicate<Order> isPremium() {
       return Order::premium;

    }

    /**
     * Function: how many items (SKUs) are in the order.
     *
     * TODO: return a lambda.
     *
     * Hint: "Order in, Integer out". Lists know their own size.
     */
    public static Function<Order, Integer> itemCount() {
        return order -> order.skus().size();
    }

    /**
     * BiPredicate: true when the order contains the given SKU.
     *
     * TODO: return a lambda with TWO parameters.
     *
     * Hint: two parameters need parentheses around them.
     */
    public static BiPredicate<Order, String> containsSku() {
        return (order,skus)->order.skus().contains(skus);
    }

    /**
     * BinaryOperator: given two orders, return the one with the larger total.
     * If the totals are equal, return the first one.
     *
     * TODO: return a lambda.
     *
     * Hint: "two Orders in, one Order out". A ternary (condition ? x : y) keeps it to one line.
     */
    public static BinaryOperator<Order> biggerOrder() {
        return ((order, order2) -> order.total()>=order2.total()? order:order2 );
    }
}
