package com.training.functionalinterfaces.solutions;

import com.training.functionalinterfaces.model.DiscountRule;

/**
 * Solution to Exercise 3 - Discount rules with a custom functional interface.
 */
public class Exercise3_DiscountRules {

    public static DiscountRule percentOff(double pct) {
        // The lambda becomes the body of DiscountRule.apply(double).
        // pct is captured; it is a parameter we never reassign, so it is effectively final.
        return price -> price * (1 - pct / 100.0);
    }

    public static DiscountRule flatOff(double amount) {
        // Math.max(0, ...) clamps the result: a 50 NOK coupon on a 30 NOK item gives 0, not -20.
        return price -> Math.max(0.0, price - amount);
    }

    public static DiscountRule then(DiscountRule first, DiscountRule second) {
        // This is exactly what Function.andThen does internally: g(f(x)).
        // Writing it by hand shows that andThen is not magic - just a lambda that
        // calls two other lambdas in order.
        return price -> second.apply(first.apply(price));
    }

    public static DiscountRule none() {
        // The "identity" rule. Useful as a safe default, or as the starting value
        // when chaining a list of rules in a loop (see Exercise 7).
        return price -> price;
    }
}
