package com.training.functionalinterfaces;

import com.training.functionalinterfaces.exercises.Exercise5_PredicateCombinators;
import com.training.functionalinterfaces.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Exercise 5 - Predicate combinators")
class Exercise5_PredicateCombinatorsTest {

    // id, paid, total, country, flaggedForFraud
    private static final Order PAID_SMALL_NO = new Order("ORD-1", true, 200, "NO", false);
    private static final Order PAID_SMALL_SE = new Order("ORD-2", true, 200, "SE", false);
    private static final Order PAID_BIG_SE = new Order("ORD-3", true, 900, "SE", false);
    private static final Order UNPAID_BIG_NO = new Order("ORD-4", false, 900, "NO", false);
    private static final Order PAID_BIG_NO_FLAGGED = new Order("ORD-5", true, 900, "NO", true);
    private static final Order UNPAID_SMALL_NO = new Order("ORD-6", false, 100, "NO", false);
    private static final Order PAID_BIG_NO = new Order("ORD-7", true, 900, "NO", false);
    private static final Order PAID_EXACTLY_500_SE = new Order("ORD-8", true, 500, "SE", false);

    @Test
    @DisplayName("express: paid + unflagged + (high value OR domestic) is eligible")
    void expressEligible() {
        Predicate<Order> express = Exercise5_PredicateCombinators.eligibleForExpressShipping();

        assertTrue(express.test(PAID_SMALL_NO), "domestic, small");
        assertTrue(express.test(PAID_BIG_SE), "high value, abroad");
        assertTrue(express.test(PAID_BIG_NO), "high value and domestic");
        assertTrue(express.test(PAID_EXACTLY_500_SE), "500 counts as high value");
    }

    @Test
    @DisplayName("express: unpaid, flagged, or small-and-abroad orders are not eligible")
    void expressNotEligible() {
        Predicate<Order> express = Exercise5_PredicateCombinators.eligibleForExpressShipping();

        assertFalse(express.test(PAID_SMALL_SE), "small and abroad");
        assertFalse(express.test(UNPAID_BIG_NO), "unpaid");
        assertFalse(express.test(PAID_BIG_NO_FLAGGED), "flagged");
        assertFalse(express.test(UNPAID_SMALL_NO), "unpaid domestic must not sneak through the OR");
    }

    @Test
    @DisplayName("manual review: flagged orders, or high-value orders going abroad")
    void manualReview() {
        Predicate<Order> review = Exercise5_PredicateCombinators.needsManualReview();

        assertTrue(review.test(PAID_BIG_NO_FLAGGED), "flagged");
        assertTrue(review.test(PAID_BIG_SE), "high value abroad");
        assertFalse(review.test(PAID_BIG_NO), "high value but domestic");
        assertFalse(review.test(PAID_SMALL_SE), "small abroad");
        assertFalse(review.test(PAID_SMALL_NO));
    }

    @Test
    @DisplayName("excluding keeps the orders that do NOT match, in original order")
    void excludingRemovesMatches() {
        List<Order> orders = List.of(PAID_SMALL_NO, PAID_BIG_NO_FLAGGED, PAID_BIG_SE, UNPAID_BIG_NO);

        List<Order> result = Exercise5_PredicateCombinators.excluding(
                orders, Exercise5_PredicateCombinators.IS_FLAGGED);

        assertEquals(List.of(PAID_SMALL_NO, PAID_BIG_SE, UNPAID_BIG_NO), result);
    }

    @Test
    @DisplayName("excluding does not modify the input list")
    void excludingDoesNotModifyInput() {
        List<Order> orders = new ArrayList<>(List.of(PAID_SMALL_NO, UNPAID_BIG_NO));

        List<Order> result = Exercise5_PredicateCombinators.excluding(
                orders, Exercise5_PredicateCombinators.IS_PAID);

        assertEquals(List.of(UNPAID_BIG_NO), result);
        assertEquals(List.of(PAID_SMALL_NO, UNPAID_BIG_NO), orders);
    }

    @Test
    @DisplayName("both: true only when both predicates are true")
    void bothIsAnd() {
        Predicate<Integer> positive = n -> n > 0;
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> p = Exercise5_PredicateCombinators.both(positive, even);

        assertTrue(p.test(4));
        assertFalse(p.test(3));
        assertFalse(p.test(-4));
    }

    @Test
    @DisplayName("both short-circuits: second is NOT called when first is false")
    void bothShortCircuits() {
        AtomicInteger secondCalls = new AtomicInteger();
        Predicate<Order> first = order -> false;
        Predicate<Order> expensiveFraudCheck = order -> {
            secondCalls.incrementAndGet();
            return true;
        };

        boolean result = Exercise5_PredicateCombinators.both(first, expensiveFraudCheck).test(PAID_SMALL_NO);

        assertFalse(result);
        assertEquals(0, secondCalls.get(), "second predicate must not be called");
    }

    @Test
    @DisplayName("both calls the second predicate when the first is true")
    void bothCallsSecondWhenNeeded() {
        AtomicInteger secondCalls = new AtomicInteger();
        Predicate<Order> second = order -> {
            secondCalls.incrementAndGet();
            return true;
        };

        assertTrue(Exercise5_PredicateCombinators.both(o -> true, second).test(PAID_SMALL_NO));
        assertEquals(1, secondCalls.get());
    }

    @Test
    @DisplayName("either short-circuits: second is NOT called when first is true")
    void eitherShortCircuits() {
        AtomicInteger secondCalls = new AtomicInteger();
        Predicate<Order> second = order -> {
            secondCalls.incrementAndGet();
            return false;
        };

        assertTrue(Exercise5_PredicateCombinators.either(o -> true, second).test(PAID_SMALL_NO));
        assertEquals(0, secondCalls.get(), "second predicate must not be called");

        assertFalse(Exercise5_PredicateCombinators.either(o -> false, second).test(PAID_SMALL_NO));
        assertEquals(1, secondCalls.get(), "now the second predicate is needed");
    }
}
