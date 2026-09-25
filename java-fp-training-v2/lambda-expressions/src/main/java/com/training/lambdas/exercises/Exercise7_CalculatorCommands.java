package com.training.lambdas.exercises;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * Exercise 7 (hard): behaviour stored as data.
 *
 * <p>Instead of a long if/else or switch over operators, store one lambda per
 * operator in a Map, then look the behaviour up by key. This "map of
 * handlers" pattern is common in backend code (command handlers, event
 * type to processor, and so on).
 */
public class Exercise7_CalculatorCommands {

    /**
     * Returns a map from operator symbol to the operation:
     * <ul>
     *   <li>{@code "+"}, {@code "-"}, {@code "*"}, {@code "/"}: the usual integer operations
     *       ({@code "/"} is integer division, so {@code 7 / 2 = 3})</li>
     *   <li>{@code "max"}, {@code "min"}: the larger / smaller of the two numbers</li>
     * </ul>
     * Use lambdas for the arithmetic and method references where an existing
     * static method already does the job.
     */
    public static Map<String, BiFunction<Integer, Integer, Integer>> operations() {
        // TODO: build and return the map.
        // Hint: Map.of("key1", value1, "key2", value2, ...) accepts up to 10 pairs.
        //       Is there a class in java.lang with static max/min methods?
        throw new UnsupportedOperationException("TODO: Exercise 7 - operations");
    }

    /**
     * Evaluates one expression of the form {@code "<number> <operator> <number>"},
     * separated by single spaces. Examples:
     * {@code "3 + 4"} gives 7, {@code "10 max 2"} gives 10.
     *
     * @throws IllegalArgumentException with message {@code "Unknown operator: <op>"}
     *         if the operator is not in {@link #operations()}
     */
    public static int evaluate(String expression) {
        // TODO: split, parse, look up the operation, apply it.
        // Hint: expression.split(" ") gives [left, operator, right].
        //       Map.get returns null for a missing key; check for that before calling apply.
        throw new UnsupportedOperationException("TODO: Exercise 7 - evaluate");
    }

    /**
     * Evaluates a chain strictly LEFT TO RIGHT (no operator precedence):
     * {@code "2 + 3 * 4"} is {@code (2 + 3) * 4 = 20}.
     * A single number such as {@code "5"} evaluates to itself.
     * Unknown operators throw exactly like {@link #evaluate(String)}.
     */
    public static int evaluateChain(String expression) {
        // TODO: keep a running result and apply each (operator, number) pair in turn.
        // Hint: after splitting, tokens[0] is the start value; then loop i = 1, 3, 5...
        //       taking tokens[i] as the operator and tokens[i + 1] as the number.
        throw new UnsupportedOperationException("TODO: Exercise 7 - evaluateChain");
    }
}
