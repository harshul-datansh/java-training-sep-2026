package com.training.demo.lambdas.demo;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;

import java.util.List;
import java.util.function.Predicate;

/**
 * DEMO 6 - Common mistakes.
 *
 * Mistake 1 (compile error): changing a captured variable - see Demo03 and Demo04.
 * Mistake 2 (compiles, but hurts): a "fat" lambda full of business logic.
 *   - it has no name, so every reader must decode it
 *   - you cannot unit test it on its own
 *   - stack traces show a generated name like lambda$main$0 instead of a meaningful one
 */
public class Demo06_CommonMistakes {

    public static void main(String[] args) {
        List<Order> orders = SampleData.orders();

        // ---------------- BAD: fat lambda ----------------
        Predicate<Order> fat = o -> {
            if (o.status() == OrderStatus.CANCELLED) {
                return false;
            }
            if (o.skus().isEmpty()) {
                return false;
            }
            if (o.premium()) {
                return true;
            }
            return o.total() >= 50;
        };

        // ---------------- GOOD: extract a named method, point to it with a method reference ----------------
        Predicate<Order> clean = Demo06_CommonMistakes::qualifiesForFreeShipping;

        System.out.println("=== Free shipping: fat lambda vs extracted method (same answers) ===");
        for (Order o : orders) {
            System.out.printf("  %s  fat=%-5s  clean=%-5s%n", o.id(), fat.test(o), clean.test(o));
        }
    }

    /**
     * A named, documented, testable rule.
     * Premium customers always get free shipping; others need a total of at least 50.
     * Cancelled or empty orders never qualify.
     */
    static boolean qualifiesForFreeShipping(Order o) {
        if (o.status() == OrderStatus.CANCELLED || o.skus().isEmpty()) {
            return false;
        }
        return o.premium() || o.total() >= 50;
    }
}
