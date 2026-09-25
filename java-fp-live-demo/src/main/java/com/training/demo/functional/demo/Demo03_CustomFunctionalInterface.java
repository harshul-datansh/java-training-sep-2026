package com.training.demo.functional.demo;

import com.training.demo.functional.DiscountRule;
import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

/**
 * DEMO 3 - Using our own functional interface (DiscountRule) with lambdas.
 *
 * Open DiscountRule.java next to this file: one abstract method discountFor(Order),
 * plus a default method plus(...) and a static method none().
 */
public class Demo03_CustomFunctionalInterface {

    public static void main(String[] args) {
        // Each lambda below IS a DiscountRule: its body becomes discountFor(order).
        DiscountRule premiumTenPercent = order -> order.premium() ? order.total() * 0.10 : 0.0;
        DiscountRule bigOrderFlat20 = order -> order.total() >= 500 ? 20.0 : 0.0;

        // Default method: glue two rules into a new rule.
        DiscountRule blackFriday = premiumTenPercent.plus(bigOrderFlat20);

        // Static method: a "do nothing" rule, handy as a starting point.
        DiscountRule noDiscount = DiscountRule.none();

        System.out.println("=== Discounts per order (EUR) ===");
        System.out.println("  id      total   premium10%  big-order  combined  none");
        for (Order o : SampleData.orders()) {
            System.out.printf("  %s %8.2f %10.2f %10.2f %9.2f %5.2f%n",
                    o.id(), o.total(),
                    premiumTenPercent.discountFor(o),
                    bigOrderFlat20.discountFor(o),
                    blackFriday.discountFor(o),
                    noDiscount.discountFor(o));
        }

        // A service method can accept ANY rule - the caller decides the behaviour.
        Order a102 = SampleData.orders().get(2);
        System.out.println();
        System.out.printf("  checkout(A-102, blackFriday) = %.2f%n", checkout(a102, blackFriday));
        System.out.printf("  checkout(A-102, a lambda written inline) = %.2f%n",
                checkout(a102, order -> 5.0));
    }

    /** Behaviour is passed in as a parameter: that is the whole point of functional interfaces. */
    static double checkout(Order order, DiscountRule rule) {
        return order.total() - rule.discountFor(order);
    }
}
