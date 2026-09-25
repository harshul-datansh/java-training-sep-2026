package com.training.lambdas.examples;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Example 07: method reference kind 2 of 4, an instance method on a
 * PARTICULAR (already existing) object. Also called a "bound" reference.
 *
 * <pre>
 *   lambda:            s -&gt; allowedCountries.contains(s)
 *   method reference:  allowedCountries::contains
 * </pre>
 *
 * The object on the LEFT of {@code ::} is chosen when the reference is created.
 * The lambda's parameters become the method's arguments.
 */
public class Example07_BoundInstanceMethodReference {

    public static void main(String[] args) {
        // One specific Set object...
        Set<String> allowedCountries = Set.of("NO", "SE", "DK");

        // ...and a Predicate that always asks THAT set.
        // (Predicate<T>: takes a T, returns boolean. Method: test(t))
        Predicate<String> isAllowedLambda = code -> allowedCountries.contains(code);
        Predicate<String> isAllowed = allowedCountries::contains;

        System.out.println("NO allowed? " + isAllowed.test("NO"));         // prints true
        System.out.println("US allowed? " + isAllowedLambda.test("US"));   // prints false

        // The most famous bound reference: System.out is one particular
        // PrintStream object, and println is an instance method on it.
        // (Consumer<T>: takes a T, returns nothing. Method: accept(t))
        Consumer<String> print = System.out::println; // same as s -> System.out.println(s)
        List.of("one", "two").forEach(print);
        // prints
        // one
        // two

        // Works with your own objects too.
        AuditLog log = new AuditLog("[AUDIT] ");
        Consumer<String> audit = log::record; // same as msg -> log.record(msg)
        audit.accept("user 42 logged in");    // prints [AUDIT] user 42 logged in
    }

    static class AuditLog {
        private final String prefix;

        AuditLog(String prefix) {
            this.prefix = prefix;
        }

        void record(String message) {
            System.out.println(prefix + message);
        }
    }
}
