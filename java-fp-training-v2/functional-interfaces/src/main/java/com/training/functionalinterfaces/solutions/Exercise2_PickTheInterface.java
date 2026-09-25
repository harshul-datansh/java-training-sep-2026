package com.training.functionalinterfaces.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
 * Solution to Exercise 2 - Pick the interface.
 */
public class Exercise2_PickTheInterface {

    public static Supplier<List<String>> newMutableList() {
        // Supplier = "no input, produce a value". A constructor reference runs
        // 'new ArrayList<>()' on EVERY get(), so each caller gets its own list.
        // Pitfall: List::of or () -> List.of() would give an immutable list.
        return ArrayList::new;
    }

    public static Predicate<String> validSku() {
        // Predicate = yes/no question. matches() checks the WHOLE string, so
        // "SKU-12345" fails (there is no need for ^ and $).
        return sku -> sku.matches("SKU-\\d{4}");
    }

    public static Function<String, String> normalizeEmail() {
        // Function<String, String> (or UnaryOperator<String>) - input and output are Strings.
        // Locale.ROOT avoids surprises on machines with e.g. a Turkish locale.
        return email -> email.trim().toLowerCase(Locale.ROOT);
    }

    public static Consumer<List<String>> appendAuditEntry(String entry) {
        // Consumer = "do something with the value, return nothing".
        // 'entry' is captured from the enclosing method (it is effectively final).
        return log -> log.add(entry);
    }

    public static BiFunction<Double, Integer, Double> lineTotal() {
        // Two inputs of different types, one result -> BiFunction<Double, Integer, Double>.
        // Auto-unboxing turns Double and Integer into double arithmetic.
        return (price, quantity) -> price * quantity;
    }

    public static UnaryOperator<String> maskAllButLast4() {
        // UnaryOperator<String>: String in, String out. A block lambda is needed
        // because there is an early-return case.
        return text -> {
            if (text.length() <= 4) {
                return text;
            }
            int hidden = text.length() - 4;
            return "*".repeat(hidden) + text.substring(hidden);
        };
    }

    public static BinaryOperator<Integer> larger() {
        // BinaryOperator<Integer>: (Integer, Integer) -> Integer.
        // Math::max matches (int, int) -> int thanks to unboxing/boxing.
        return Math::max;
    }

    public static BiPredicate<String, Integer> hasAtLeastChars() {
        // Two inputs, boolean answer -> BiPredicate. Its method is test(T, U).
        return (text, n) -> text.length() >= n;
    }

    public static BiConsumer<Map<String, Integer>, String> incrementCounter() {
        // BiConsumer: two inputs, no result - it changes the map (a side effect).
        // merge puts 1 if the key is missing, otherwise combines old + 1 with Integer::sum.
        return (counts, key) -> counts.merge(key, 1, Integer::sum);
    }
}
