package com.training.demo.lambdas.solutions;

import com.training.demo.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** SOLUTION to exercise 3. */
public class Ex03_MethodReferences {

    /** Why: s -> Integer.parseInt(s) only forwards to a static method -> ClassName::staticMethod. */
    public static Function<String, Integer> parseQuantity() {
        return Integer::parseInt;
    }

    /** Why: the list object is chosen NOW (bound); the SKU arrives later as the argument. */
    public static Predicate<String> isSkuIn(Order order) {
        return order.skus()::contains;
    }

    /** Why: o -> o.id() calls a method ON the parameter -> ClassName::instanceMethod (unbound). */
    public static Function<Order, String> idOf() {
        return Order::id;
    }

    /** Why: () -> new ArrayList<>() only calls a constructor -> ClassName::new. */
    public static Supplier<List<String>> newSkuList() {
        return ArrayList::new;
    }
}
