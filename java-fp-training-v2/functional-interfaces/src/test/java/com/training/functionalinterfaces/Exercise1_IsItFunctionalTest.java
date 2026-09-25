package com.training.functionalinterfaces;

import com.training.functionalinterfaces.exercises.Exercise1_IsItFunctional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Exercise 1 - Is it functional?")
class Exercise1_IsItFunctionalTest {

    @Test
    @DisplayName("A: one abstract method")
    void priceFormatter() {
        assertTrue(Exercise1_IsItFunctional.isPriceFormatterFunctional());
    }

    @Test
    @DisplayName("B: two abstract methods")
    void orderValidator() {
        assertFalse(Exercise1_IsItFunctional.isOrderValidatorFunctional());
    }

    @Test
    @DisplayName("C: one abstract + a default + a static method")
    void taxCalculator() {
        assertTrue(Exercise1_IsItFunctional.isTaxCalculatorFunctional());
    }

    @Test
    @DisplayName("D: one abstract + a redeclared equals(Object)")
    void skuComparator() {
        assertTrue(Exercise1_IsItFunctional.isSkuComparatorFunctional());
    }

    @Test
    @DisplayName("E: extends a functional interface and adds an abstract method")
    void auditedFormatter() {
        assertFalse(Exercise1_IsItFunctional.isAuditedFormatterFunctional());
    }

    @Test
    @DisplayName("F: extends a functional interface and adds only a default method")
    void euroFormatter() {
        assertTrue(Exercise1_IsItFunctional.isEuroFormatterFunctional());
    }

    @Test
    @DisplayName("G: only a default method, no abstract method")
    void tagged() {
        assertFalse(Exercise1_IsItFunctional.isTaggedFunctional());
    }

    @Test
    @DisplayName("A lambda implements tax(); totalWithTax() comes from the default method")
    void twentyFivePercentTaxIsALambdaImplementation() {
        Exercise1_IsItFunctional.TaxCalculator vat = Exercise1_IsItFunctional.twentyFivePercentTax();

        assertEquals(25.0, vat.tax(100.0), 1e-9);
        assertEquals(125.0, vat.totalWithTax(100.0), 1e-9);
        assertEquals(0.0, vat.tax(0.0), 1e-9);
    }
}
