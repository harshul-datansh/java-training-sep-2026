package com.training.functionalinterfaces.exercises;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Exercise 7 - A tiny generic rule engine (hard)
 *
 * <p>Write GENERIC helpers that work for any type {@code T}: combine any number of
 * predicates, chain any number of transformations, and report which named rules
 * failed. This is the kind of utility a validation or pricing service is built on.
 *
 * <p>Example:
 * <pre>{@code
 * Predicate<String> ok = allOf(List.of(s -> !s.isBlank(), s -> s.length() <= 10));
 * ok.test("hello");          // true
 * ok.test("far too long!");  // false
 *
 * UnaryOperator<String> clean = pipeline(List.of(String::trim, String::toUpperCase));
 * clean.apply("  sku-1 ");   // "SKU-1"
 * }</pre>
 */
public class Exercise7_RuleEngine {

    /**
     * A predicate that is true only if EVERY predicate in the list is true.
     * An EMPTY list gives a predicate that is always true (nothing failed).
     * Should stop at the first false predicate (short-circuit).
     */
    public static <T> Predicate<T> allOf(List<Predicate<T>> predicates) {
        // TODO: combine all predicates with AND
        // Hint: start from a predicate that is always true, and in a loop keep replacing it with
        //       "itself and the next one". Why is "always true" the right starting value?
        throw new UnsupportedOperationException("TODO: Exercise 7 - allOf");
    }

    /**
     * A predicate that is true if AT LEAST ONE predicate in the list is true.
     * An EMPTY list gives a predicate that is always false (nothing matched).
     * Should stop at the first true predicate (short-circuit).
     */
    public static <T> Predicate<T> anyOf(List<Predicate<T>> predicates) {
        // TODO: combine all predicates with OR
        // Hint: same loop shape as allOf - but pick the starting value that does not change the result of OR.
        throw new UnsupportedOperationException("TODO: Exercise 7 - anyOf");
    }

    /**
     * Chains the operators so they run in LIST ORDER: the first operator in the
     * list is applied first. An EMPTY list gives an operator that returns its input unchanged.
     *
     * <p>Example: {@code pipeline(List.of(s -> s + "a", s -> s + "b")).apply("x")} returns {@code "xab"}.
     */
    public static <T> UnaryOperator<T> pipeline(List<UnaryOperator<T>> operators) {
        // TODO: chain the operators in list order
        // Hint: UnaryOperator.identity() is a good start. Careful: andThen on a UnaryOperator returns a
        //       Function<T, T>, not a UnaryOperator<T> - so think about what type your loop variable should
        //       have, and how to turn it back into a UnaryOperator at the end (a lambda or method reference works).
        throw new UnsupportedOperationException("TODO: Exercise 7 - pipeline");
    }

    /**
     * Tests {@code value} against every named rule and returns the NAMES of the
     * rules that failed, in the map's iteration order. Returns an empty list if all pass.
     *
     * <p>Example: rules {"notBlank": ..., "maxLength10": ...} with value "far too long!"
     * returns {@code ["maxLength10"]}.
     */
    public static <T> List<String> failedRules(T value, Map<String, Predicate<T>> rules) {
        // TODO: collect the names of the rules whose test(value) is false
        // Hint: loop over rules.entrySet() (or use Map.forEach with a BiConsumer). A LinkedHashMap
        //       keeps insertion order, so iterating it is enough to get the order right.
        throw new UnsupportedOperationException("TODO: Exercise 7 - failedRules");
    }
}
