package com.training.demo.functional.exercises;

import com.training.demo.functional.DiscountRule;

/**
 * EXERCISE 2 (medium) - implement our own functional interface with lambdas.
 *
 * Open com.training.demo.functional.DiscountRule first: it has ONE abstract method,
 * discountFor(Order), which returns the discount in EUR.
 *
 * Run the test:  mvn test -Dtest=Ex02_CustomDiscountRulesTest
 */
public class Ex02_CustomDiscountRules {

    /**
     * Premium customers get 10% of the total off. Everybody else gets 0.
     *
     * TODO: return a lambda that is a DiscountRule.
     *
     * Hint: the lambda receives an Order and must return a double.
     */
    public static DiscountRule premiumTenPercent() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Orders with a total of at least minTotal get a flat amount off. Others get 0.
     *
     * TODO: return a lambda that uses both parameters.
     *
     * Hint: same capture idea as the lambdas exercise 2.
     */
    public static DiscountRule flatOffAbove(double minTotal, double amount) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Both rules together: premium 10% PLUS 20 EUR off orders of at least 500.
     *
     * TODO: build it from the two methods above.
     *
     * Hint: do not write a new lambda - DiscountRule has a default method for this.
     */
    public static DiscountRule blackFriday() {
        throw new UnsupportedOperationException("TODO");
    }
}
