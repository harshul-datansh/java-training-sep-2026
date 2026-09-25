package com.training.functionalinterfaces.solutions;

import com.training.functionalinterfaces.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Solution to Exercise 5 - Predicate combinators.
 */
public class Exercise5_PredicateCombinators {

    // ---- Given base predicates (identical to the exercise) --------------------

    public static final Predicate<Order> IS_PAID = Order::paid;
    public static final Predicate<Order> IS_FLAGGED = Order::flaggedForFraud;
    public static final Predicate<Order> IS_HIGH_VALUE = order -> order.total() >= 500;
    public static final Predicate<Order> IS_DOMESTIC = order -> "NO".equals(order.country());

    // ---- Solutions ------------------------------------------------------------

    public static Predicate<Order> eligibleForExpressShipping() {
        // Grouping matters. Build "(high value OR domestic)" as its own predicate
        // so it is ONE argument to and(...). Writing
        //   IS_PAID.and(IS_FLAGGED.negate()).and(IS_HIGH_VALUE).or(IS_DOMESTIC)
        // would mean "(paid AND not flagged AND high) OR domestic" - an unpaid
        // domestic order would sneak through.
        Predicate<Order> highValueOrDomestic = IS_HIGH_VALUE.or(IS_DOMESTIC);
        return IS_PAID
                .and(IS_FLAGGED.negate())
                .and(highValueOrDomestic);
    }

    public static Predicate<Order> needsManualReview() {
        // The nested call IS the brackets: flagged OR (high AND not domestic).
        // Predicate.not(IS_DOMESTIC) and IS_DOMESTIC.negate() mean the same thing.
        return IS_FLAGGED.or(IS_HIGH_VALUE.and(Predicate.not(IS_DOMESTIC)));
    }

    public static List<Order> excluding(List<Order> orders, Predicate<Order> unwanted) {
        // Predicate.not reads better than "!unwanted.test(o)" and gives us a
        // reusable "keep" predicate. We build a NEW list so the caller's list is
        // untouched (it might even be immutable, e.g. List.of(...)).
        Predicate<Order> keep = Predicate.not(unwanted);
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (keep.test(order)) {
                result.add(order);
            }
        }
        return result;
    }

    public static <T> Predicate<T> both(Predicate<T> first, Predicate<T> second) {
        // Predicate.and is implemented as  t -> first.test(t) && second.test(t),
        // so it short-circuits exactly like &&.
        return first.and(second);
    }

    public static <T> Predicate<T> either(Predicate<T> first, Predicate<T> second) {
        // Predicate.or uses ||, so 'second' is skipped when 'first' is already true.
        return first.or(second);
    }
}
