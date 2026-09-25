package com.training.functionalinterfaces.examples;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Example 03 - Function and BiFunction: "take input(s), give back a result".
 *
 * <pre>
 *   Function&lt;T, R&gt;        R apply(T t)
 *   BiFunction&lt;T, U, R&gt;   R apply(T t, U u)
 * </pre>
 *
 * The LAST type parameter is always the result type.
 */
public class Example03_FunctionAndBiFunction {

    public static void main(String[] args) {
        // Function<String, Integer>: takes a String, returns an Integer.
        Function<String, Integer> length = s -> s.length();
        System.out.println("length(\"backend\")      = " + length.apply("backend"));
        // prints length("backend")      = 7

        // Same idea as a method reference (Topic 1).
        Function<String, String> upper = String::toUpperCase;
        System.out.println("upper(\"order\")         = " + upper.apply("order"));
        // prints upper("order")         = ORDER

        // Input and output types can be completely different.
        Function<Integer, String> orderId = n -> "ORD-" + n;
        System.out.println("orderId(1001)          = " + orderId.apply(1001));
        // prints orderId(1001)          = ORD-1001

        // BiFunction<Double, Integer, Double>: two inputs, one result.
        BiFunction<Double, Integer, Double> lineTotal = (price, qty) -> price * qty;
        System.out.println("lineTotal(19.5, 4)     = " + lineTotal.apply(19.5, 4));
        // prints lineTotal(19.5, 4)     = 78.0

        // BiFunction with a method reference: String.repeat(int) is (String, int) -> String
        BiFunction<String, Integer, String> repeat = String::repeat;
        System.out.println("repeat(\"ab\", 3)        = " + repeat.apply("ab", 3));
        // prints repeat("ab", 3)        = ababab
    }
}
