package com.training.lambdas.solutions;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/** Solution to Exercise 2. */
public class Exercise2_SyntaxForms {

    public static Supplier<String> defaultUsername() {
        // Zero parameters: the empty "()" is mandatory, there is no shorter form.
        return () -> "guest";
    }

    public static Function<String, String> shout() {
        // One inferred parameter: parentheses can be dropped.
        // Expression body: the expression's value is returned, no "return".
        return s -> s.toUpperCase() + "!";
    }

    public static BiFunction<String, Integer, String> repeat() {
        // Two parameters: parentheses are required again.
        // times is an Integer; String.repeat(int) unboxes it automatically.
        return (text, times) -> text.repeat(times);
    }
}
