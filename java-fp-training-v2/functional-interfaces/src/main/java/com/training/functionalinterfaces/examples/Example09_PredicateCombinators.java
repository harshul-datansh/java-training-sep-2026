package com.training.functionalinterfaces.examples;

import java.util.function.Predicate;

/**
 * Example 09 - Predicate.and / or / negate, and short-circuiting.
 *
 * <pre>
 *   p.and(q)    true only if BOTH are true    (like &amp;&amp;)
 *   p.or(q)     true if EITHER is true        (like ||)
 *   p.negate()  the opposite of p             (like !)
 * </pre>
 *
 * Just like {@code &&} and {@code ||}, and/or SHORT-CIRCUIT:
 * <ul>
 *   <li>{@code p.and(q)}: if p is false, q is never called (the answer is already false).</li>
 *   <li>{@code p.or(q)}:  if p is true,  q is never called (the answer is already true).</li>
 * </ul>
 * This matters when q is expensive (a database call) or has side effects.
 */
public class Example09_PredicateCombinators {

    public static void main(String[] args) {
        // Two predicates about an order total that print when they are called.
        Predicate<Integer> isPositive = total -> {
            System.out.println("    isPositive(" + total + ")?");
            return total > 0;
        };
        Predicate<Integer> isLarge = total -> {
            System.out.println("    isLarge(" + total + ")?");
            return total >= 500;
        };

        Predicate<Integer> positiveAndLarge = isPositive.and(isLarge);

        System.out.println("positiveAndLarge.test(800):");
        boolean a = positiveAndLarge.test(800);
        // prints     isPositive(800)?
        // prints     isLarge(800)?
        System.out.println("  -> " + a);
        // prints   -> true

        System.out.println("positiveAndLarge.test(-5):");
        boolean b = positiveAndLarge.test(-5);
        // prints     isPositive(-5)?
        // (isLarge is NOT called: first one was false, so the answer is already false)
        System.out.println("  -> " + b);
        // prints   -> false

        Predicate<Integer> positiveOrLarge = isPositive.or(isLarge);
        System.out.println("positiveOrLarge.test(10):");
        boolean c = positiveOrLarge.test(10);
        // prints     isPositive(10)?
        // (isLarge is NOT called: first one was true, so the answer is already true)
        System.out.println("  -> " + c);
        // prints   -> true

        Predicate<Integer> notLarge = isLarge.negate();
        System.out.println("notLarge.test(800):");
        boolean d = notLarge.test(800);
        // prints     isLarge(800)?
        System.out.println("  -> " + d);
        // prints   -> false
    }
}
