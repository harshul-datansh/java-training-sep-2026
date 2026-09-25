package com.training.functionalinterfaces;

import com.training.functionalinterfaces.exercises.Exercise3_DiscountRules;
import com.training.functionalinterfaces.model.DiscountRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exercise 3 - Discount rules")
class Exercise3_DiscountRulesTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("percentOff takes a percentage off the price")
    void percentOff() {
        assertEquals(180.0, Exercise3_DiscountRules.percentOff(10).apply(200.0), DELTA);
        assertEquals(60.0, Exercise3_DiscountRules.percentOff(25).apply(80.0), DELTA);
        assertEquals(80.0, Exercise3_DiscountRules.percentOff(0).apply(80.0), DELTA);
        assertEquals(0.0, Exercise3_DiscountRules.percentOff(100).apply(80.0), DELTA);
    }

    @Test
    @DisplayName("flatOff subtracts a fixed amount")
    void flatOff() {
        assertEquals(70.0, Exercise3_DiscountRules.flatOff(50).apply(120.0), DELTA);
    }

    @Test
    @DisplayName("flatOff never goes below zero")
    void flatOffNeverNegative() {
        assertEquals(0.0, Exercise3_DiscountRules.flatOff(50).apply(30.0), DELTA);
        assertEquals(0.0, Exercise3_DiscountRules.flatOff(50).apply(50.0), DELTA);
    }

    @Test
    @DisplayName("then applies the first rule, then the second: 10% off then 20 off")
    void thenPercentThenFlat() {
        DiscountRule rule = Exercise3_DiscountRules.then(
                Exercise3_DiscountRules.percentOff(10), Exercise3_DiscountRules.flatOff(20));

        assertEquals(70.0, rule.apply(100.0), DELTA); // 100 -> 90 -> 70
    }

    @Test
    @DisplayName("then with the rules swapped gives a different price: 20 off then 10% off")
    void thenFlatThenPercent() {
        DiscountRule rule = Exercise3_DiscountRules.then(
                Exercise3_DiscountRules.flatOff(20), Exercise3_DiscountRules.percentOff(10));

        assertEquals(72.0, rule.apply(100.0), DELTA); // 100 -> 80 -> 72
    }

    @Test
    @DisplayName("then calls each rule exactly once, first before second, feeding the result along")
    void thenCallsInOrderAndPassesResult() {
        List<String> calls = new ArrayList<>();
        DiscountRule first = price -> {
            calls.add("first(" + price + ")");
            return price - 1;
        };
        DiscountRule second = price -> {
            calls.add("second(" + price + ")");
            return price * 2;
        };

        double result = Exercise3_DiscountRules.then(first, second).apply(10.0);

        assertEquals(18.0, result, DELTA);
        assertEquals(List.of("first(10.0)", "second(9.0)"), calls);
    }

    @Test
    @DisplayName("none returns the price unchanged, and is neutral when chained")
    void none() {
        assertEquals(99.5, Exercise3_DiscountRules.none().apply(99.5), DELTA);

        DiscountRule chained = Exercise3_DiscountRules.then(
                Exercise3_DiscountRules.none(), Exercise3_DiscountRules.percentOff(50));
        assertEquals(50.0, chained.apply(100.0), DELTA);
    }
}
