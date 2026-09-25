package com.training.lambdas.exercises;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Exercise 3 (medium): lambdas that need a block body { ... }.
 *
 * <p>Each task needs more than one step, so you will need braces, and
 * therefore an explicit {@code return}.
 */
public class Exercise3_BlockBodies {

    /**
     * Masks a card number for logging: remove all spaces, then replace every
     * digit except the last 4 with {@code '*'}.
     * <ul>
     *   <li>{@code "1234 5678 1234 5678"} becomes {@code "************5678"}</li>
     *   <li>Numbers with 4 or fewer digits (after removing spaces) are returned unchanged:
     *       {@code "12 3"} becomes {@code "123"}</li>
     * </ul>
     */
    public static Function<String, String> maskCardNumber() {
        // TODO: return a lambda with a block body.
        // Hint: step 1 remove spaces (String.replace), step 2 check the length,
        //       step 3 build "*" repeated (length - 4) times + the last 4 characters
        //       (String.repeat and String.substring).
        throw new UnsupportedOperationException("TODO: Exercise 3 - maskCardNumber");
    }

    /**
     * Sorts by length (shortest first); strings of equal length are sorted
     * alphabetically.
     * Example: [pear, fig, apple, kiwi] becomes [fig, kiwi, pear, apple].
     */
    public static Comparator<String> byLengthThenAlphabetical() {
        // TODO: two-parameter lambda with a block body.
        // Hint: compare the lengths first. Only if that result is 0 do you need
        //       a second comparison. Remember what compareTo returns.
        throw new UnsupportedOperationException("TODO: Exercise 3 - byLengthThenAlphabetical");
    }

    /**
     * Maps an HTTP status code to a category:
     * 200-299 {@code "SUCCESS"}, 400-499 {@code "CLIENT_ERROR"},
     * 500-599 {@code "SERVER_ERROR"}, anything else {@code "OTHER"}.
     */
    public static Function<Integer, String> httpStatusCategory() {
        // TODO: one-parameter lambda with if/else inside a block body.
        // Hint: every path through the block must end in a return.
        throw new UnsupportedOperationException("TODO: Exercise 3 - httpStatusCategory");
    }
}
