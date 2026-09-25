package com.training.demo.functional.solutions;

import java.util.function.Function;

/** SOLUTION to exercise 4. */
public class Ex04_PricePipeline {

    public static final Function<Double, Double> APPLY_COUPON = price -> price - 50;
    public static final Function<Double, Double> ADD_VAT = price -> price * 1.25;

    /** Why: andThen reads left to right - coupon, then VAT. */
    public static Function<Double, Double> couponThenVat() {
        return APPLY_COUPON.andThen(ADD_VAT);
    }

    /** Why: ADD_VAT.compose(APPLY_COUPON) means ADD_VAT(APPLY_COUPON(x)) - the argument runs first. */
    public static Function<Double, Double> couponThenVatWithCompose() {
        return ADD_VAT.compose(APPLY_COUPON);
    }

    /** Why: identity() is the neutral "no change" step, so both branches have the same shape. */
    public static Function<Double, Double> pricingFor(boolean hasCoupon) {
        Function<Double, Double> firstStep = hasCoupon ? APPLY_COUPON : Function.identity();
        return firstStep.andThen(ADD_VAT);
    }
}
