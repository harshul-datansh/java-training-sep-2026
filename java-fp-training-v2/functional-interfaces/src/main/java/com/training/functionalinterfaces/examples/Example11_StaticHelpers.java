package com.training.functionalinterfaces.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Example 11 - Static helper methods: Function.identity(), Predicate.not(), Predicate.isEqual().
 *
 * <p>These are STATIC methods on the interfaces, so you call them on the
 * interface name, not on an instance.
 */
public class Example11_StaticHelpers {

    public static void main(String[] args) {
        // --- Function.identity(): "give back exactly what you got" -----------
        // Useful as a "do nothing" starting point, or when an API demands a
        // Function but you don't want to change the value.
        Function<String, String> same = Function.identity();
        System.out.println("identity(\"SKU-1\")  = " + same.apply("SKU-1"));
        // prints identity("SKU-1")  = SKU-1

        // A "do nothing" start makes chaining in a loop easy:
        Function<String, String> pipeline = Function.identity();
        pipeline = pipeline.andThen(String::trim).andThen(String::toUpperCase);
        System.out.println("pipeline(\"  abc \") = " + pipeline.apply("  abc "));
        // prints pipeline("  abc ") = ABC

        // --- Predicate.not(p): same as p.negate(), but works on a method reference
        // (Java 11+). You can't write String::isBlank.negate() - a method reference
        // has no type until it is assigned, so there is nothing to call negate() on.
        // javac says:  error: method reference not expected here
        List<String> names = new ArrayList<>(List.of("Ola", " ", "Kari", ""));
        names.removeIf(Predicate.not(s -> s.length() > 1)); // remove the short/empty ones
        System.out.println("after removeIf(not(len > 1)) = " + names);
        // prints after removeIf(not(len > 1)) = [Ola, Kari]

        Predicate<String> hasText = Predicate.not(String::isBlank);
        System.out.println("hasText(\"  \")      = " + hasText.test("  "));
        // prints hasText("  ")      = false

        // --- Predicate.isEqual(x): "is it equal to x?" (null-safe, uses equals) -
        Predicate<String> isNorway = Predicate.isEqual("NO");
        System.out.println("isEqual(NO)(\"NO\")  = " + isNorway.test("NO"));
        // prints isEqual(NO)("NO")  = true
        System.out.println("isEqual(NO)(null)  = " + isNorway.test(null));
        // prints isEqual(NO)(null)  = false
    }
}
