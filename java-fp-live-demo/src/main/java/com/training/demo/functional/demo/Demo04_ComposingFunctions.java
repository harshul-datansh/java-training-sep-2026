package com.training.demo.functional.demo;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * DEMO 4 - Default and static helper methods: building big behaviour from small pieces.
 *
 *   f.andThen(g)   = first f, then g       ("and then")
 *   f.compose(g)   = first g, then f       (read it as "f of g")
 *   p.and(q), p.or(q), p.negate(), Predicate.not(p)
 *   Function.identity() = "give back exactly what you got"
 */
public class Demo04_ComposingFunctions {

    public static void main(String[] args) {
        // Two small price steps. They PRINT so we can see the order they run in.
        Function<Double, Double> applyCoupon = price -> {
            System.out.println("    applyCoupon(" + price + ") -> " + (price - 50));
            return price - 50;
        };
        Function<Double, Double> addVat = price -> {
            System.out.println("    addVat(" + price + ") -> " + (price * 1.25));
            return price * 1.25;
        };

        System.out.println("=== andThen: coupon FIRST, then VAT ===");
        Function<Double, Double> couponThenVat = applyCoupon.andThen(addVat);
        System.out.println("  result: " + couponThenVat.apply(200.0));

        System.out.println("=== compose: the ARGUMENT runs first -> coupon first, then VAT (same as above) ===");
        Function<Double, Double> sameViaCompose = addVat.compose(applyCoupon);
        System.out.println("  result: " + sameViaCompose.apply(200.0));

        System.out.println("=== order matters: VAT first, then coupon ===");
        Function<Double, Double> vatThenCoupon = applyCoupon.compose(addVat);
        System.out.println("  result: " + vatThenCoupon.apply(200.0));
        System.out.println();

        // Function.identity(): useful as a "no change" step, e.g. when a step is optional.
        boolean customerHasCoupon = false;
        Function<Double, Double> firstStep = customerHasCoupon ? applyCoupon : Function.identity();
        System.out.println("=== identity() as an optional step (no coupon) ===");
        System.out.println("  result: " + firstStep.andThen(addVat).apply(200.0));
        System.out.println();

        // Predicates combine the same way.
        Predicate<Order> isPaid = o -> o.status() == OrderStatus.PAID;
        Predicate<Order> isPremium = Order::premium;
        Predicate<Order> isCancelled = o -> o.status() == OrderStatus.CANCELLED;

        Predicate<Order> paidAndPremium = isPaid.and(isPremium);
        Predicate<Order> paidOrPremium = isPaid.or(isPremium);
        Predicate<Order> notPremium = isPremium.negate();
        Predicate<Order> notCancelled = Predicate.not(isCancelled); // reads well inside filter(...)

        List<Order> orders = SampleData.orders();
        System.out.println("=== Predicate combinations ===");
        System.out.println("  paid AND premium : " + ids(orders, paidAndPremium));
        System.out.println("  paid OR premium  : " + ids(orders, paidOrPremium));
        System.out.println("  NOT premium      : " + ids(orders, notPremium));
        System.out.println("  not(cancelled)   : " + ids(orders, notCancelled));
    }

    private static List<String> ids(List<Order> orders, Predicate<Order> p) {
        return orders.stream().filter(p).map(Order::id).toList();
    }
}
