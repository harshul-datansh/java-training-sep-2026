package com.training.lambdas.examples;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Example 06: method reference kind 1 of 4, a STATIC method.
 *
 * <p>A method reference is a shorter way to write a lambda whose whole body
 * is "call this one existing method with my parameters".
 *
 * <pre>
 *   lambda:            s -&gt; Integer.parseInt(s)
 *   method reference:  Integer::parseInt
 * </pre>
 *
 * Read {@code ::} as "use the method called ... on ...".
 * The lambda's parameters are passed to the static method, in order.
 */
public class Example06_StaticMethodReference {

    public static void main(String[] args) {
        // JDK static method
        Function<String, Integer> parseLambda = s -> Integer.parseInt(s);
        Function<String, Integer> parseRef = Integer::parseInt;
        System.out.println("lambda: " + parseLambda.apply("42")); // prints 42
        System.out.println("ref:    " + parseRef.apply("42"));    // prints 42

        // Two parameters work the same way: (a, b) -> Math.max(a, b)
        BiFunction<Integer, Integer, Integer> max = Math::max;
        System.out.println("max:    " + max.apply(3, 7)); // prints 7

        // Your own static methods work too.
        Function<String, String> slug = Example06_StaticMethodReference::toSlug;
        System.out.println("slug:   " + slug.apply("  Hello Big World ")); // prints hello-big-world
    }

    static String toSlug(String text) {
        return text.trim().toLowerCase().replace(' ', '-');
    }
}
