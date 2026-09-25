package com.training.lambdas.solutions;

import com.training.lambdas.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** Solution to Exercise 5. */
public class Exercise5_MethodReferences {

    public static Function<String, Integer> parseQuantity() {
        // Kind 1: the lambda only forwards its argument to a static method.
        return Integer::parseInt;
    }

    public static Predicate<String> isKnownCurrency(Set<String> knownCurrencies) {
        // Kind 2: the method is always called on ONE specific object (this set),
        // chosen right now. The lambda's parameter becomes contains()'s argument.
        return knownCurrencies::contains;
    }

    public static Function<String, String> trim() {
        // Kind 3: there is no object yet. The Function's parameter IS the object
        // trim() is called on. Class name on the left, instance method on the right.
        return String::trim;
    }

    public static BiFunction<String, String, Boolean> sameIgnoringCase() {
        // Kind 3 with two parameters: first parameter -> the object,
        // second parameter -> the argument. i.e. (a, b) -> a.equalsIgnoreCase(b)
        return String::equalsIgnoreCase;
    }

    public static Function<String, Customer> customerFromName() {
        // Kind 4: Customer has two constructors. Function.apply takes ONE
        // argument, so the compiler picks Customer(String name).
        return Customer::new;
    }

    public static Supplier<List<String>> newAuditLog() {
        // Kind 4 with no arguments. List is an interface, so we reference a
        // concrete class. Each get() calls the constructor again -> a new list.
        return ArrayList::new;
    }
}
