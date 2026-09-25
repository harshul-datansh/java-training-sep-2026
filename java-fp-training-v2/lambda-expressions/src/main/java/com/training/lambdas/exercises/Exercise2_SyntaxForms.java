package com.training.lambdas.exercises;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Exercise 2 (easy): zero, one and two parameter lambdas with expression bodies.
 *
 * <p>Reminder of the interfaces (see Example02_ParameterForms):
 * <ul>
 *   <li>{@code Supplier<T>}: no input, returns T</li>
 *   <li>{@code Function<T, R>}: one input, returns R</li>
 *   <li>{@code BiFunction<T, U, R>}: two inputs, returns R</li>
 * </ul>
 * Use the SHORTEST legal form each time: no braces, no {@code return}, and no
 * parentheses around a single parameter.
 */
public class Exercise2_SyntaxForms {

    /** A Supplier that always returns {@code "guest"}. */
    public static Supplier<String> defaultUsername() {
        // TODO: zero-parameter lambda.
        // Hint: with no parameters you still need something before the arrow.
        throw new UnsupportedOperationException("TODO: Exercise 2 - defaultUsername");
    }

    /** Upper-cases the input and adds an exclamation mark: {@code "hi"} becomes {@code "HI!"}. */
    public static Function<String, String> shout() {
        // TODO: one-parameter lambda, expression body.
        // Hint: String has a method that upper-cases; + joins strings.
        throw new UnsupportedOperationException("TODO: Exercise 2 - shout");
    }

    /** Repeats a text n times: {@code ("ab", 3)} becomes {@code "ababab"}. */
    public static BiFunction<String, Integer, String> repeat() {
        // TODO: two-parameter lambda, expression body.
        // Hint: since Java 11, String has a method that does exactly this.
        throw new UnsupportedOperationException("TODO: Exercise 2 - repeat");
    }
}
