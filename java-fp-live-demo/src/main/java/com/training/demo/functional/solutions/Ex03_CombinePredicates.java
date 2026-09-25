package com.training.demo.functional.solutions;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;

import java.util.function.Predicate;

/** SOLUTION to exercise 3. */
public class Ex03_CombinePredicates {

    public static final Predicate<Order> IS_PAID = o -> o.status() == OrderStatus.PAID;
    public static final Predicate<Order> IS_SHIPPED = o -> o.status() == OrderStatus.SHIPPED;
    public static final Predicate<Order> IS_PREMIUM = Order::premium;
    public static final Predicate<Order> IS_BIG = o -> o.total() >= 500;
    public static final Predicate<Order> IS_CANCELLED = o -> o.status() == OrderStatus.CANCELLED;

    /** Why: and(...) is true only when both are true (and stops early if the first is false). */
    public static Predicate<Order> paidAndBig() {
        return IS_PAID.and(IS_BIG);
    }

    /** Why: negate() flips a predicate; reads as "big and not premium". */
    public static Predicate<Order> needsReview() {
        return IS_BIG.and(IS_PREMIUM.negate());
    }

    /** Why: or(...) is true when at least one side is true (and stops early if the first is true). */
    public static Predicate<Order> premiumOrBig() {
        return IS_PREMIUM.or(IS_BIG);
    }

    /** Why: Predicate.not(...) is the static twin of negate(); it reads like the business rule. */
    public static Predicate<Order> stillOpen() {
        return Predicate.not(IS_CANCELLED).and(Predicate.not(IS_SHIPPED));
    }
}
