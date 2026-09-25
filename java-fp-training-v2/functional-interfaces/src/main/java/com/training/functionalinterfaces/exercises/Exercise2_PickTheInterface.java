package com.training.functionalinterfaces.exercises;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Exercise 2 - Pick the interface (easy)
 *
 * <p>The return type of each method already tells you WHICH functional interface
 * to use. Your job: return a lambda or method reference of that type that does
 * the small backend task described.
 *
 * <p>Nothing is computed inside these methods themselves - each method just
 * RETURNS a piece of behaviour. The caller runs it later.
 *
 * <p>Example: {@code normalizeEmail().apply("  Kari@Example.COM ")}
 * returns {@code "kari@example.com"}.
 */
public class Exercise2_PickTheInterface {

    /**
     * A factory for fresh, MUTABLE lists. Every {@code get()} returns a new empty list.
     *
     * <p>Example: {@code newMutableList().get()} returns {@code []}, and you can {@code add} to it.
     */
    public static Supplier<List<String>> newMutableList() {
        // TODO: return a Supplier that creates a new mutable list on every get()
        // Hint: a constructor reference (Topic 1) fits Supplier perfectly. List.of() is NOT mutable.
        throw new UnsupportedOperationException("TODO: Exercise 2 - newMutableList");
    }

    /**
     * A valid SKU is exactly "SKU-" followed by exactly 4 digits.
     *
     * <p>Examples: "SKU-0042" is valid; "SKU-42", "SKU-12345", "sku-0042", "SKU-00a2" are not.
     */
    public static Predicate<String> validSku() {
        // TODO: return a Predicate that tests the format described above
        // Hint: String.matches(regex) returns a boolean. In a regex, \\d means "a digit" and {4} means "exactly 4".
        throw new UnsupportedOperationException("TODO: Exercise 2 - validSku");
    }

    /**
     * Normalises an e-mail address: remove surrounding whitespace, then lower-case it.
     *
     * <p>Example: {@code "  Kari@Example.COM "} becomes {@code "kari@example.com"}.
     */
    public static Function<String, String> normalizeEmail() {
        // TODO: return a Function that trims and lower-cases
        // Hint: a lambda calling two String methods one after the other is fine here.
        throw new UnsupportedOperationException("TODO: Exercise 2 - normalizeEmail");
    }

    /**
     * A Consumer that appends the given audit entry to whatever list it is handed.
     *
     * <p>Example: {@code appendAuditEntry("user logged in").accept(log)} adds
     * {@code "user logged in"} to the end of {@code log}.
     */
    public static Consumer<List<String>> appendAuditEntry(String entry) {
        // TODO: return a Consumer that adds 'entry' to the list it receives
        // Hint: the lambda's parameter is the LIST; 'entry' is captured from the method parameter.
        throw new UnsupportedOperationException("TODO: Exercise 2 - appendAuditEntry");
    }

    /**
     * Line total of an order line: unit price multiplied by quantity.
     *
     * <p>Example: {@code lineTotal().apply(19.5, 4)} returns {@code 78.0}.
     */
    public static BiFunction<Double, Integer, Double> lineTotal() {
        // TODO: return a BiFunction (price, quantity) -> total
        // Hint: BiFunction<T, U, R> - the first two types are the inputs, the last is the result.
        throw new UnsupportedOperationException("TODO: Exercise 2 - lineTotal");
    }

    /**
     * Masks all characters except the last 4 with '*'. Strings of length 4 or
     * less are returned unchanged.
     *
     * <p>Examples: "4111222233334444" becomes "************4444"; "1234" stays "1234".
     */
    public static UnaryOperator<String> maskAllButLast4() {
        // TODO: return a UnaryOperator that masks the string
        // Hint: you will need a block lambda { ... return ...; }. Look at String.repeat and String.substring.
        throw new UnsupportedOperationException("TODO: Exercise 2 - maskAllButLast4");
    }

    /**
     * Returns the larger of two integers.
     *
     * <p>Example: {@code larger().apply(3, 7)} returns {@code 7}.
     */
    public static BinaryOperator<Integer> larger() {
        // TODO: return a BinaryOperator that picks the larger value
        // Hint: there is a static method in java.lang.Math (or Integer) with exactly this shape.
        throw new UnsupportedOperationException("TODO: Exercise 2 - larger");
    }

    /**
     * Tests whether a string has at least {@code n} characters.
     *
     * <p>Examples: ("password", 8) is true; ("pass", 8) is false.
     */
    public static BiPredicate<String, Integer> hasAtLeastChars() {
        // TODO: return a BiPredicate (text, n) -> boolean
        // Hint: BiPredicate's method is test(T, U). Compare the length with n.
        throw new UnsupportedOperationException("TODO: Exercise 2 - hasAtLeastChars");
    }

    /**
     * Increments the counter stored for {@code key} in the map. A key that is not
     * there yet starts at 0, so after one increment it is 1.
     *
     * <p>Example: with an empty map, calling it with "login" twice leaves {@code {login=2}}.
     */
    public static BiConsumer<Map<String, Integer>, String> incrementCounter() {
        // TODO: return a BiConsumer (map, key) that increments the count for key
        // Hint: Map.merge(key, 1, ...) handles both "missing" and "already there". Integer::sum may help.
        throw new UnsupportedOperationException("TODO: Exercise 2 - incrementCounter");
    }
}
