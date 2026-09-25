package com.training.functionalinterfaces.exercises;

import com.training.functionalinterfaces.model.Order;

import java.util.List;
import java.util.function.Predicate;

/**
 * Exercise 5 - Predicate combinators: and / or / negate / Predicate.not (medium)
 *
 * <p>An order-fulfilment service needs business rules over {@link Order}.
 * The four BASE predicates are given. Build the business rules by COMBINING
 * them with {@code and}, {@code or}, {@code negate} and {@code Predicate.not} -
 * do not write a new lambda that re-checks the fields by hand.
 *
 * <p>Example: a paid, unflagged order of 200 NOK to Norway ("NO") is eligible for
 * express shipping; the same order to Sweden ("SE") is not (it is under 500 and not domestic).
 */
public class Exercise5_PredicateCombinators {

    // ---- Given base predicates (already implemented, do not change) ----------

    public static final Predicate<Order> IS_PAID = Order::paid;
    public static final Predicate<Order> IS_FLAGGED = Order::flaggedForFraud;
    public static final Predicate<Order> IS_HIGH_VALUE = order -> order.total() >= 500;
    public static final Predicate<Order> IS_DOMESTIC = order -> "NO".equals(order.country());

    // ---- Your tasks -----------------------------------------------------------

    /**
     * Express shipping: paid AND NOT flagged AND (high value OR domestic).
     *
     * <p>Examples: paid, unflagged, 900 NOK to "SE" is eligible (high value);
     * unpaid 900 NOK to "NO" is not; paid, FLAGGED, 900 NOK to "NO" is not.
     */
    public static Predicate<Order> eligibleForExpressShipping() {
        // TODO: combine the base predicates
        // Hint: build the (high value OR domestic) part first as its own Predicate, then and(...) it in.
        //       For "NOT flagged", look at negate().
        throw new UnsupportedOperationException("TODO: Exercise 5 - eligibleForExpressShipping");
    }

    /**
     * Manual review: flagged for fraud, OR (high value AND NOT domestic).
     *
     * <p>Examples: any flagged order needs review; an unflagged 900 NOK order to "SE"
     * needs review; an unflagged 900 NOK order to "NO" does not.
     */
    public static Predicate<Order> needsManualReview() {
        // TODO: combine the base predicates
        // Hint: or(...) and and(...) are just method calls - use brackets (a nested call) to control grouping.
        throw new UnsupportedOperationException("TODO: Exercise 5 - needsManualReview");
    }

    /**
     * Returns a NEW list containing the orders that do NOT match {@code unwanted},
     * in their original order. The input list must not be changed.
     *
     * <p>Example: {@code excluding(orders, IS_FLAGGED)} returns only the unflagged orders.
     */
    public static List<Order> excluding(List<Order> orders, Predicate<Order> unwanted) {
        // TODO: build and return a new list with the orders that fail 'unwanted'
        // Hint: Predicate.not(unwanted) gives you the "keep" test. Copy into a new ArrayList with a loop,
        //       or copy first and use removeIf - but never modify the list you were given.
        throw new UnsupportedOperationException("TODO: Exercise 5 - excluding");
    }

    /**
     * Combines two predicates with AND. Must short-circuit: if {@code first} is
     * false, {@code second} must NOT be called (it may be an expensive fraud-service call).
     */
    public static <T> Predicate<T> both(Predicate<T> first, Predicate<T> second) {
        // TODO: return a predicate that is true only when both are true
        // Hint: one default method on Predicate already does this, including the short-circuit.
        throw new UnsupportedOperationException("TODO: Exercise 5 - both");
    }

    /**
     * Combines two predicates with OR. Must short-circuit: if {@code first} is
     * true, {@code second} must NOT be called.
     */
    public static <T> Predicate<T> either(Predicate<T> first, Predicate<T> second) {
        // TODO: return a predicate that is true when at least one is true
        // Hint: the partner of the method you used in both(...).
        throw new UnsupportedOperationException("TODO: Exercise 5 - either");
    }
}
