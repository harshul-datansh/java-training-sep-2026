package com.training.functionalinterfaces.model;

/**
 * A custom functional interface: "given a price, return the discounted price".
 *
 * <p>It has exactly ONE abstract method, so any lambda of the shape
 * {@code double -> double} can become a {@code DiscountRule}:
 *
 * <pre>{@code
 * DiscountRule tenPercent = price -> price * 0.90;
 * tenPercent.apply(200.0);   // 180.0
 * }</pre>
 *
 * <p>Used by Exercise 3. It lives in the {@code model} package so the exercise
 * and the solution share exactly the same type.
 */
@FunctionalInterface
public interface DiscountRule {

    /**
     * @param price the price before this rule is applied
     * @return the price after this rule is applied
     */
    double apply(double price);
}
