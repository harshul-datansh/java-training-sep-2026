package com.training.functionalinterfaces.exercises;

import com.training.functionalinterfaces.model.DiscountRule;

/**
 * Exercise 3 - Discount rules with a custom functional interface (easy/medium)
 *
 * <p>Open {@link DiscountRule} in the {@code model} package first. It is our OWN
 * functional interface: one abstract method, {@code double apply(double price)}.
 * Because it is functional, every factory method below can simply return a lambda.
 *
 * <p>Build a tiny discount toolkit for a checkout service:
 * <ul>
 *   <li>{@code percentOff(10).apply(200.0)} returns {@code 180.0}</li>
 *   <li>{@code flatOff(50).apply(30.0)} returns {@code 0.0} (never below zero)</li>
 *   <li>{@code then(percentOff(10), flatOff(20)).apply(100.0)} returns {@code 70.0}
 *       (10 % off first gives 90, then 20 off gives 70)</li>
 *   <li>{@code none().apply(99.5)} returns {@code 99.5}</li>
 * </ul>
 */
public class Exercise3_DiscountRules {

    /**
     * A rule that takes {@code pct} percent off the price.
     *
     * <p>Example: {@code percentOff(25).apply(80.0)} returns {@code 60.0}.
     *
     * @param pct percentage between 0 and 100
     */
    public static DiscountRule percentOff(double pct) {
        // TODO: return a DiscountRule lambda
        // Hint: the lambda parameter is the price; pct is captured from the method. 10 % off means "keep 90 %".
        throw new UnsupportedOperationException("TODO: Exercise 3 - percentOff");
    }

    /**
     * A rule that subtracts a fixed {@code amount}, but never goes below 0.
     *
     * <p>Examples: {@code flatOff(50).apply(120.0)} returns {@code 70.0};
     * {@code flatOff(50).apply(30.0)} returns {@code 0.0}.
     */
    public static DiscountRule flatOff(double amount) {
        // TODO: return a DiscountRule lambda that cannot produce a negative price
        // Hint: java.lang.Math has a method that picks the bigger of two numbers.
        throw new UnsupportedOperationException("TODO: Exercise 3 - flatOff");
    }

    /**
     * Chains two rules: apply {@code first}, then apply {@code second} to the result.
     *
     * <p>Example: {@code then(flatOff(20), percentOff(10)).apply(100.0)} returns {@code 72.0}
     * (100 - 20 = 80, then 10 % off gives 72). Note this differs from the example in
     * the class Javadoc: order matters!
     */
    public static DiscountRule then(DiscountRule first, DiscountRule second) {
        // TODO: return a new DiscountRule that runs first, then second
        // Hint: DiscountRule has no andThen (it is our own interface), so write the chaining yourself
        //       inside a lambda: call first.apply(...) and feed its result into second.apply(...).
        throw new UnsupportedOperationException("TODO: Exercise 3 - then");
    }

    /**
     * A rule that gives no discount at all: the price comes back unchanged.
     *
     * <p>Example: {@code none().apply(99.5)} returns {@code 99.5}.
     */
    public static DiscountRule none() {
        // TODO: return a DiscountRule that changes nothing
        // Hint: think of Function.identity(), but for our own interface.
        throw new UnsupportedOperationException("TODO: Exercise 3 - none");
    }
}
