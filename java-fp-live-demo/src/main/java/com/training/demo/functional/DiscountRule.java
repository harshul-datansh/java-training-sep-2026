package com.training.demo.functional;

import com.training.demo.model.Order;

/**
 * Our own functional interface: "a rule that calculates a discount (in EUR) for an order".
 *
 * Why not just use Function&lt;Order, Double&gt;?
 *   - the NAME tells readers what it is for (DiscountRule vs a generic "Function")
 *   - the method name discountFor(...) reads well at the call site
 *   - we can add domain-specific default/static helpers (plus, none)
 *   - it returns a primitive double, so no boxing to Double
 */
@FunctionalInterface
public interface DiscountRule {

    /** The ONE abstract method. A lambda assigned to DiscountRule becomes this method's body. */
    double discountFor(Order order);

    /** Default method: does not count toward "single abstract method". Combines two rules. */
    default DiscountRule plus(DiscountRule other) {
        return order -> this.discountFor(order) + other.discountFor(order);
    }

    /** Static method: also does not count. A rule that never gives a discount. */
    static DiscountRule none() {
        return order -> 0.0;
    }
}
