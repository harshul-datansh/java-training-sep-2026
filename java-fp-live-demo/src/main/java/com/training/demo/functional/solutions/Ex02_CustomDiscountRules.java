package com.training.demo.functional.solutions;

import com.training.demo.functional.DiscountRule;

/** SOLUTION to exercise 2. */
public class Ex02_CustomDiscountRules {

    /** Why: the lambda body becomes DiscountRule.discountFor(order). */
    public static DiscountRule premiumTenPercent() {
        return order -> order.premium() ? order.total() * 0.10 : 0.0;
    }

    /** Why: minTotal and amount are effectively final, so the lambda can capture them. */
    public static DiscountRule flatOffAbove(double minTotal, double amount) {
        return order -> order.total() >= minTotal ? amount : 0.0;
    }

    /** Why: the default method plus(...) combines two rules without any new logic. */
    public static DiscountRule blackFriday() {
        return premiumTenPercent().plus(flatOffAbove(500, 20));
    }
}
