package com.training.functionalinterfaces;

import com.training.functionalinterfaces.exercises.Exercise2_PickTheInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Exercise 2 - Pick the interface")
class Exercise2_PickTheInterfaceTest {

    @Test
    @DisplayName("Supplier gives a new, empty, mutable list on every get()")
    void newMutableListReturnsFreshMutableLists() {
        Supplier<List<String>> supplier = Exercise2_PickTheInterface.newMutableList();

        List<String> first = supplier.get();
        List<String> second = supplier.get();

        assertTrue(first.isEmpty());
        assertNotSame(first, second, "each get() must create a new list");
        first.add("x"); // must not throw: the list has to be mutable
        assertEquals(List.of("x"), first);
        assertTrue(second.isEmpty(), "lists must not share state");
    }

    @Test
    @DisplayName("validSku accepts SKU- followed by exactly 4 digits")
    void validSkuAcceptsCorrectFormat() {
        Predicate<String> valid = Exercise2_PickTheInterface.validSku();

        assertTrue(valid.test("SKU-0042"));
        assertTrue(valid.test("SKU-9999"));
    }

    @Test
    @DisplayName("validSku rejects wrong prefix, wrong length and non-digits")
    void validSkuRejectsBadFormats() {
        Predicate<String> valid = Exercise2_PickTheInterface.validSku();

        assertFalse(valid.test("SKU-42"));
        assertFalse(valid.test("SKU-12345"));
        assertFalse(valid.test("sku-0042"));
        assertFalse(valid.test("SKU-00a2"));
        assertFalse(valid.test("XSKU-0042"));
        assertFalse(valid.test(""));
    }

    @Test
    @DisplayName("normalizeEmail trims and lower-cases")
    void normalizeEmailTrimsAndLowercases() {
        Function<String, String> normalize = Exercise2_PickTheInterface.normalizeEmail();

        assertEquals("kari@example.com", normalize.apply("  Kari@Example.COM "));
        assertEquals("ola@test.no", normalize.apply("ola@test.no"));
    }

    @Test
    @DisplayName("appendAuditEntry adds the entry to the end of the given list")
    void appendAuditEntryAppends() {
        List<String> log = new ArrayList<>(List.of("existing"));

        Exercise2_PickTheInterface.appendAuditEntry("user logged in").accept(log);
        Exercise2_PickTheInterface.appendAuditEntry("user logged out").accept(log);

        assertEquals(List.of("existing", "user logged in", "user logged out"), log);
    }

    @Test
    @DisplayName("lineTotal multiplies price by quantity")
    void lineTotalMultiplies() {
        assertEquals(78.0, Exercise2_PickTheInterface.lineTotal().apply(19.5, 4), 1e-9);
        assertEquals(0.0, Exercise2_PickTheInterface.lineTotal().apply(19.5, 0), 1e-9);
    }

    @Test
    @DisplayName("maskAllButLast4 hides everything except the last 4 characters")
    void maskAllButLast4Masks() {
        UnaryOperator<String> mask = Exercise2_PickTheInterface.maskAllButLast4();

        assertEquals("************4444", mask.apply("4111222233334444"));
        assertEquals("*2345", mask.apply("12345"));
    }

    @Test
    @DisplayName("maskAllButLast4 leaves strings of 4 characters or less unchanged")
    void maskAllButLast4LeavesShortStringsAlone() {
        UnaryOperator<String> mask = Exercise2_PickTheInterface.maskAllButLast4();

        assertEquals("1234", mask.apply("1234"));
        assertEquals("12", mask.apply("12"));
        assertEquals("", mask.apply(""));
    }

    @Test
    @DisplayName("larger returns the bigger of two integers")
    void largerPicksMax() {
        BinaryOperator<Integer> larger = Exercise2_PickTheInterface.larger();

        assertEquals(7, larger.apply(3, 7));
        assertEquals(7, larger.apply(7, 3));
        assertEquals(-1, larger.apply(-1, -5));
    }

    @Test
    @DisplayName("hasAtLeastChars is true when length >= n")
    void hasAtLeastChars() {
        BiPredicate<String, Integer> atLeast = Exercise2_PickTheInterface.hasAtLeastChars();

        assertTrue(atLeast.test("password", 8));
        assertTrue(atLeast.test("password1", 8));
        assertFalse(atLeast.test("pass", 8));
    }

    @Test
    @DisplayName("incrementCounter starts missing keys at 1 and increments existing ones")
    void incrementCounterCounts() {
        BiConsumer<Map<String, Integer>, String> increment = Exercise2_PickTheInterface.incrementCounter();
        Map<String, Integer> counts = new HashMap<>();

        increment.accept(counts, "login");
        increment.accept(counts, "login");
        increment.accept(counts, "logout");

        assertEquals(Map.of("login", 2, "logout", 1), counts);
    }
}
