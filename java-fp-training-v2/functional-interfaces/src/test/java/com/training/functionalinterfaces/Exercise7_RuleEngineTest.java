package com.training.functionalinterfaces;

import com.training.functionalinterfaces.exercises.Exercise7_RuleEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Exercise 7 - Rule engine")
class Exercise7_RuleEngineTest {

    private static final Predicate<String> NOT_BLANK = s -> !s.isBlank();
    private static final Predicate<String> MAX_10 = s -> s.length() <= 10;
    private static final Predicate<String> STARTS_WITH_SKU = s -> s.startsWith("SKU-");

    @Test
    @DisplayName("allOf is true only if every predicate is true")
    void allOfCombinesWithAnd() {
        Predicate<String> ok = Exercise7_RuleEngine.allOf(List.of(NOT_BLANK, MAX_10, STARTS_WITH_SKU));

        assertTrue(ok.test("SKU-1"));
        assertFalse(ok.test("SKU-123456789"), "too long");
        assertFalse(ok.test("ABC-1"), "wrong prefix");
    }

    @Test
    @DisplayName("allOf of an empty list is always true")
    void allOfEmptyIsTrue() {
        Predicate<String> ok = Exercise7_RuleEngine.allOf(List.of());

        assertTrue(ok.test("anything"));
        assertTrue(ok.test(""));
    }

    @Test
    @DisplayName("allOf stops at the first false predicate")
    void allOfShortCircuits() {
        List<String> called = new ArrayList<>();
        Predicate<String> a = s -> { called.add("a"); return true; };
        Predicate<String> b = s -> { called.add("b"); return false; };
        Predicate<String> c = s -> { called.add("c"); return true; };

        assertFalse(Exercise7_RuleEngine.allOf(List.of(a, b, c)).test("x"));
        assertEquals(List.of("a", "b"), called);
    }

    @Test
    @DisplayName("anyOf is true if at least one predicate is true")
    void anyOfCombinesWithOr() {
        Predicate<String> any = Exercise7_RuleEngine.anyOf(List.of(STARTS_WITH_SKU, s -> s.endsWith("!")));

        assertTrue(any.test("SKU-1"));
        assertTrue(any.test("hello!"));
        assertFalse(any.test("hello"));
    }

    @Test
    @DisplayName("anyOf of an empty list is always false")
    void anyOfEmptyIsFalse() {
        Predicate<String> any = Exercise7_RuleEngine.anyOf(List.of());

        assertFalse(any.test("anything"));
    }

    @Test
    @DisplayName("anyOf stops at the first true predicate")
    void anyOfShortCircuits() {
        List<String> called = new ArrayList<>();
        Predicate<String> a = s -> { called.add("a"); return false; };
        Predicate<String> b = s -> { called.add("b"); return true; };
        Predicate<String> c = s -> { called.add("c"); return true; };

        assertTrue(Exercise7_RuleEngine.anyOf(List.of(a, b, c)).test("x"));
        assertEquals(List.of("a", "b"), called);
    }

    @Test
    @DisplayName("pipeline applies operators in list order")
    void pipelineRunsInListOrder() {
        UnaryOperator<String> p = Exercise7_RuleEngine.pipeline(List.of(s -> s + "a", s -> s + "b", s -> s + "c"));

        assertEquals("xabc", p.apply("x"));
    }

    @Test
    @DisplayName("pipeline: order matters (trim then add brackets vs the reverse)")
    void pipelineOrderMatters() {
        UnaryOperator<String> trim = String::trim;
        UnaryOperator<String> bracket = s -> "[" + s + "]";

        assertEquals("[sku-1]", Exercise7_RuleEngine.pipeline(List.of(trim, bracket)).apply("  sku-1 "));
        assertEquals("[  sku-1 ]", Exercise7_RuleEngine.pipeline(List.of(bracket, trim)).apply("  sku-1 "));
    }

    @Test
    @DisplayName("pipeline works for other types too, e.g. Integer")
    void pipelineIsGeneric() {
        UnaryOperator<Integer> p = Exercise7_RuleEngine.pipeline(List.of(n -> n * 2, n -> n + 3));

        assertEquals(13, p.apply(5)); // (5 * 2) + 3
    }

    @Test
    @DisplayName("pipeline of an empty list returns the input unchanged")
    void pipelineEmptyIsIdentity() {
        UnaryOperator<String> p = Exercise7_RuleEngine.pipeline(List.of());

        assertEquals("unchanged", p.apply("unchanged"));
    }

    @Test
    @DisplayName("failedRules returns names of failing rules in insertion order")
    void failedRulesInOrder() {
        Map<String, Predicate<String>> rules = new LinkedHashMap<>();
        rules.put("notBlank", NOT_BLANK);
        rules.put("startsWithSku", STARTS_WITH_SKU);
        rules.put("maxLength10", MAX_10);

        assertEquals(List.of("startsWithSku", "maxLength10"),
                Exercise7_RuleEngine.failedRules("far too long!", rules));
        assertEquals(List.of("maxLength10"),
                Exercise7_RuleEngine.failedRules("SKU-far too long", rules));
    }

    @Test
    @DisplayName("failedRules returns an empty list when every rule passes")
    void failedRulesAllPass() {
        Map<String, Predicate<String>> rules = new LinkedHashMap<>();
        rules.put("notBlank", NOT_BLANK);
        rules.put("maxLength10", MAX_10);

        assertEquals(List.of(), Exercise7_RuleEngine.failedRules("SKU-1", rules));
    }

    @Test
    @DisplayName("failedRules works for non-String types")
    void failedRulesIsGeneric() {
        Map<String, Predicate<Integer>> rules = new LinkedHashMap<>();
        rules.put("positive", n -> n > 0);
        rules.put("even", n -> n % 2 == 0);
        rules.put("underLimit", n -> n < 100);

        assertEquals(List.of("positive"), Exercise7_RuleEngine.failedRules(-2, rules));
        assertEquals(List.of("even", "underLimit"), Exercise7_RuleEngine.failedRules(101, rules));
    }
}
