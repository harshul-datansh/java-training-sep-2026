package com.training.functionalinterfaces.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Example 05 - Predicate and BiPredicate: "a yes/no question".
 *
 * <pre>
 *   Predicate&lt;T&gt;        boolean test(T t)
 *   BiPredicate&lt;T, U&gt;   boolean test(T t, U u)
 * </pre>
 *
 * Note the method name is test(...), NOT apply(...).
 * It returns a primitive boolean, so there is no null / unboxing worry.
 */
public class Example05_PredicateAndBiPredicate {

    public static void main(String[] args) {
        Predicate<String> isBlank = String::isBlank;
        System.out.println("isBlank(\"   \")    = " + isBlank.test("   "));
        // prints isBlank("   ")    = true

        Predicate<Integer> isPositive = n -> n > 0;
        System.out.println("isPositive(-3)     = " + isPositive.test(-3));
        // prints isPositive(-3)     = false

        // Many JDK methods accept a Predicate. ArrayList.removeIf is one:
        List<String> skus = new ArrayList<>(List.of("SKU-0001", "", "SKU-0002", "  "));
        skus.removeIf(isBlank);
        System.out.println("After removeIf:    " + skus);
        // prints After removeIf:    [SKU-0001, SKU-0002]

        // BiPredicate: a yes/no question about TWO values.
        BiPredicate<String, Integer> hasAtLeast = (s, n) -> s.length() >= n;
        System.out.println("hasAtLeast(\"abc\", 5) = " + hasAtLeast.test("abc", 5));
        // prints hasAtLeast("abc", 5) = false

        // A method reference can be a BiPredicate too: String.startsWith(String)
        // has the shape (String, String) -> boolean.
        BiPredicate<String, String> startsWith = String::startsWith;
        System.out.println("startsWith(\"SKU-1\", \"SKU-\") = " + startsWith.test("SKU-1", "SKU-"));
        // prints startsWith("SKU-1", "SKU-") = true
    }
}
