package com.training.demo.lambdas.exercises;

import com.training.demo.model.Order;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * EXERCISE 3 (medium) - one method reference of each kind.
 *
 * Each method below can be solved with a lambda, but try to write a
 * METHOD REFERENCE (something with ::) instead.
 *
 * Run the test:  mvn test -Dtest=Ex03_MethodReferencesTest
 */
public class Ex03_MethodReferences {

    /**
     * STATIC kind. Turns a quantity typed into a form ("3") into a number (3).
     *
     * TODO: return a method reference.
     *
     * Hint: which class in java.lang has a static method that turns a String into an int?
     */
    public static Function<String, Integer> parseQuantity() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * BOUND kind (a particular object). True when the given SKU is in THIS order.
     *
     * TODO: return a method reference.
     *
     * Hint: the "particular object" is the list you get from order.skus().
     */
    public static Predicate<String> isSkuIn(Order order) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * UNBOUND kind (an arbitrary object). Gets the id of whatever order it is given.
     *
     * TODO: return a method reference.
     *
     * Hint: records generate a getter method for every field.
     */
    public static Function<Order, String> idOf() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * CONSTRUCTOR kind. Every call must give a NEW, EMPTY, MODIFIABLE list.
     *
     * TODO: return a method reference.
     *
     * Hint: List is an interface - pick a class that implements it.
     */
    public static Supplier<List<String>> newSkuList() {
        throw new UnsupportedOperationException("TODO");
    }
}
