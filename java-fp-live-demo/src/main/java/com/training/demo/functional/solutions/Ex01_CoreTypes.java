package com.training.demo.functional.solutions;

import com.training.demo.model.Order;

import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/** SOLUTION to exercise 1. */
public class Ex01_CoreTypes {

    /** Why: the record getter premium() already returns the boolean we need. */
    public static Predicate<Order> isPremium() {
        return Order::premium;
    }

    /** Why: T -> R where R is Integer; size() is autoboxed to Integer. */
    public static Function<Order, Integer> itemCount() {
        return o -> o.skus().size();
    }

    /** Why: two inputs, boolean output = BiPredicate. */
    public static BiPredicate<Order, String> containsSku() {
        return (o, sku) -> o.skus().contains(sku);
    }

    /** Why: >= keeps the first order on a tie. This shape is exactly what reduce(...) wants later. */
    public static BinaryOperator<Order> biggerOrder() {
        return (a, b) -> a.total() >= b.total() ? a : b;
    }
}
