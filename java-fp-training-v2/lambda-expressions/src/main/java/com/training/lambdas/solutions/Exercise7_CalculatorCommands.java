package com.training.lambdas.solutions;

import java.util.Map;
import java.util.function.BiFunction;

/** Solution to Exercise 7. */
public class Exercise7_CalculatorCommands {

    public static Map<String, BiFunction<Integer, Integer, Integer>> operations() {
        // Each value is a piece of behaviour stored like any other object.
        // Math.max/Math.min already exist as static methods, so kind-1 method
        // references are shorter and clearer than (a, b) -> Math.max(a, b).
        return Map.of(
                "+", (a, b) -> a + b,
                "-", (a, b) -> a - b,
                "*", (a, b) -> a * b,
                "/", (a, b) -> a / b,
                "max", Math::max,
                "min", Math::min
        );
    }

    public static int evaluate(String expression) {
        String[] tokens = expression.split(" ");
        return apply(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]));
    }

    public static int evaluateChain(String expression) {
        String[] tokens = expression.split(" ");
        int result = Integer.parseInt(tokens[0]);
        // Walk the (operator, number) pairs, feeding each result into the next step.
        for (int i = 1; i < tokens.length; i += 2) {
            result = apply(result, tokens[i], Integer.parseInt(tokens[i + 1]));
        }
        return result;
    }

    // Shared by both methods: look the behaviour up by key instead of a switch.
    // Adding a new operator now means adding one map entry, nothing else.
    private static int apply(int left, String operator, int right) {
        BiFunction<Integer, Integer, Integer> operation = operations().get(operator);
        if (operation == null) {
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
        return operation.apply(left, right);
    }
}
