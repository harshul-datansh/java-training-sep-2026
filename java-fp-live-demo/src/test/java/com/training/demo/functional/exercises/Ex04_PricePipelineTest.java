package com.training.demo.functional.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex04_PricePipelineTest {

    private static final double CENT = 0.001;

    @Test
    void couponIsTakenOffBeforeVat() {
        // 200 - 50 = 150, then * 1.25 = 187.5   (the wrong order would give 200.0)
        assertEquals(187.5, Ex04_PricePipeline.couponThenVat().apply(200.0), CENT);
    }

    @Test
    void composeVersionGivesTheSameResult() {
        assertEquals(187.5, Ex04_PricePipeline.couponThenVatWithCompose().apply(200.0), CENT);
        assertEquals(62.5, Ex04_PricePipeline.couponThenVatWithCompose().apply(100.0), CENT);
    }

    @Test
    void pricingWithAndWithoutCoupon() {
        assertEquals(187.5, Ex04_PricePipeline.pricingFor(true).apply(200.0), CENT);
        assertEquals(250.0, Ex04_PricePipeline.pricingFor(false).apply(200.0), CENT);
    }
}
