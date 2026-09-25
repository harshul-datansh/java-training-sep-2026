package com.training.lambdas.examples;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Example 02: the PARAMETER part of a lambda.
 *
 * <pre>
 *   (parameters)  -&gt;  body
 *   ^^^^^^^^^^^^
 *   this file
 * </pre>
 *
 * Quick guide to the interfaces used here (Topic 2 covers them properly):
 * <ul>
 *   <li>{@code Supplier<T>}          : no input, returns T.      Method: get()</li>
 *   <li>{@code Function<T, R>}       : one input T, returns R.   Method: apply(t)</li>
 *   <li>{@code BiFunction<T, U, R>}  : two inputs, returns R.    Method: apply(t, u)</li>
 * </ul>
 */
public class Example02_ParameterForms {

    public static void main(String[] args) {
        // ZERO parameters: empty parentheses are REQUIRED.
        Supplier<String> defaultName = () -> "guest";
        System.out.println("zero params:      " + defaultName.get()); // prints guest

        // ONE parameter with its type written out.
        Function<String, Integer> lengthTyped = (String s) -> s.length();

        // ONE parameter, type inferred. The compiler reads Function<String, Integer>
        // and knows s must be a String, so we don't have to say it.
        Function<String, Integer> lengthInferred = (s) -> s.length();

        // ONE parameter, inferred, NO parentheses. This is the shorthand
        // you'll see most often. Only allowed with exactly one parameter
        // and no written type.
        Function<String, Integer> length = s -> s.length();

        System.out.println("one param, typed: " + lengthTyped.apply("lambda"));    // prints 6
        System.out.println("one param, (s):   " + lengthInferred.apply("lambda")); // prints 6
        System.out.println("one param, s:     " + length.apply("lambda"));         // prints 6

        // TWO or more parameters: parentheses are REQUIRED.
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("two params:       " + add.apply(2, 3)); // prints 5

        // Parameter names are yours to choose, just like method parameters.
        // Pick names that say what the value is.
        BiFunction<Double, Integer, Double> lineTotal = (price, quantity) -> price * quantity;
        System.out.println("named well:       " + lineTotal.apply(9.5, 2)); // prints 19.0

        // Things that do NOT compile (try uncommenting one):
        // Function<String, Integer> bad1 = String s -> s.length();   // a typed param needs ( )
        // BiFunction<Integer, Integer, Integer> bad2 = a, b -> a + b; // two params need ( )
        // BiFunction<Integer, Integer, Integer> bad3 = (int a, b) -> a + b; // all typed or none
    }
}
