package com.training.lambdas;

import com.training.lambdas.exercises.Exercise5_MethodReferences;
import com.training.lambdas.model.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Exercise5_MethodReferencesTest {

    @Test
    @DisplayName("kind 1: parseQuantity parses integers")
    void parseQuantity() {
        assertEquals(42, Exercise5_MethodReferences.parseQuantity().apply("42"));
        assertEquals(-7, Exercise5_MethodReferences.parseQuantity().apply("-7"));
    }

    @Test
    @DisplayName("kind 2: isKnownCurrency checks the given set")
    void isKnownCurrency() {
        Predicate<String> known = Exercise5_MethodReferences.isKnownCurrency(Set.of("NOK", "EUR"));
        assertTrue(known.test("NOK"));
        assertFalse(known.test("USD"));
    }

    @Test
    @DisplayName("kind 3: trim removes surrounding whitespace")
    void trim() {
        assertEquals("order-1", Exercise5_MethodReferences.trim().apply("  order-1 \t"));
    }

    @Test
    @DisplayName("kind 3 with two params: sameIgnoringCase")
    void sameIgnoringCase() {
        assertTrue(Exercise5_MethodReferences.sameIgnoringCase().apply("Kafka", "KAFKA"));
        assertFalse(Exercise5_MethodReferences.sameIgnoringCase().apply("Kafka", "Spring"));
    }

    @Test
    @DisplayName("kind 4: customerFromName uses the one-argument constructor")
    void customerFromName() {
        Customer ada = Exercise5_MethodReferences.customerFromName().apply("Ada");
        assertEquals(new Customer("Ada", "ada@example.com"), ada);
    }

    @Test
    @DisplayName("kind 4: newAuditLog gives a fresh modifiable list every time")
    void newAuditLog() {
        Supplier<List<String>> factory = Exercise5_MethodReferences.newAuditLog();
        List<String> first = factory.get();
        List<String> second = factory.get();

        first.add("entry");
        assertEquals(List.of("entry"), first);
        assertTrue(second.isEmpty());
        assertNotSame(first, second);
    }
}
