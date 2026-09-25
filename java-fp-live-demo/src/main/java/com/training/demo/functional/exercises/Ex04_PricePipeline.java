package com.training.demo.functional.exercises;

import java.util.function.Function;

/**
 * EXERCISE 4 (hard) - chain functions, and get the ORDER right.
 *
 * Business rule: the 50 EUR coupon is taken off BEFORE VAT is added.
 * Example: 200 -> coupon -> 150 -> VAT -> 187.5
 *
 * Use the building blocks below with andThen, compose and Function.identity().
 *
 * Run the test:  mvn test -Dtest=Ex04_PricePipelineTest
 */
public class Ex04_PricePipeline {

    public static final Function<Double, Double> APPLY_COUPON = price -> price - 50;
    public static final Function<Double, Double> ADD_VAT = price -> price * 1.25;

    /**
     * Coupon first, then VAT - written with andThen.
     *
     * TODO: chain the two building blocks.
     *
     * Hint: f.andThen(g) runs f first.
     */
    public static Function<Double, Double> couponThenVat() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * EXACTLY the same result as couponThenVat(), but written with compose.
     *
     * TODO: chain the two building blocks.
     *
     * Hint: with compose, the function passed as the ARGUMENT runs first.
     */
    public static Function<Double, Double> couponThenVatWithCompose() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Pricing depends on whether the customer has a coupon:
     *   hasCoupon = true  -> coupon, then VAT   (200 -> 187.5)
     *   hasCoupon = false -> VAT only           (200 -> 250.0)
     *
     * TODO: pick the first step, then always add VAT.
     *
     * Hint: you need a "do nothing" first step for the no-coupon case.
     * Function has a static method for exactly that.
     */
    public static Function<Double, Double> pricingFor(boolean hasCoupon) {
        throw new UnsupportedOperationException("TODO");
    }
}
