package com.training.functionalinterfaces.exercises;

import java.util.Locale;
import java.util.function.Function;

/**
 * Exercise 4 - Function composition with andThen and compose (medium)
 *
 * <p>Small, single-purpose functions are GIVEN below as constants. Do not
 * rewrite their logic - build bigger functions by COMBINING them with
 * {@code andThen} and {@code compose}.
 *
 * <p>Example: {@code slugBuilder().apply("  Hello, World 2024! ")} returns
 * {@code "hello-world-2024"} - the kind of URL slug a CMS backend creates from a blog title.
 */
public class Exercise4_FunctionComposition {

    // ---- Given building blocks (already implemented, do not change) ----------

    /** Removes leading/trailing whitespace. */
    public static final Function<String, String> TRIM = String::trim;

    /** Lower-cases the text. */
    public static final Function<String, String> LOWERCASE = s -> s.toLowerCase(Locale.ROOT);

    /** Replaces each run of one or more whitespace characters with a single hyphen. */
    public static final Function<String, String> SPACES_TO_HYPHENS = s -> s.replaceAll("\\s+", "-");

    /** Removes every character that is not a-z, 0-9 or a hyphen. */
    public static final Function<String, String> STRIP_INVALID = s -> s.replaceAll("[^a-z0-9-]", "");

    /** Adds 25 % Norwegian VAT. */
    public static final Function<Double, Double> ADD_VAT = price -> price * 1.25;

    /** Rounds to the nearest whole krone. */
    public static final Function<Double, Double> ROUND = price -> (double) Math.round(price);

    /** Formats a price for a receipt line. */
    public static final Function<Double, String> FORMAT_NOK = price -> "NOK " + price;

    // ---- Your tasks -----------------------------------------------------------

    /**
     * Builds a URL slug: TRIM, then LOWERCASE, then SPACES_TO_HYPHENS, then STRIP_INVALID.
     *
     * <p>Example: {@code "  Hello, World 2024! "} becomes {@code "hello-world-2024"}.
     */
    public static Function<String, String> slugBuilder() {
        // TODO: chain the four given functions in the order above
        // Hint: Function.andThen runs the function on the left first, then the one you pass in.
        throw new UnsupportedOperationException("TODO: Exercise 4 - slugBuilder");
    }

    /**
     * Adds VAT first, then rounds.
     *
     * <p>Example: {@code 10.4} becomes {@code 13.0} (10.4 * 1.25 = 13.0, rounded 13.0).
     */
    public static Function<Double, Double> vatThenRounded() {
        // TODO: combine ADD_VAT and ROUND so that VAT happens first
        // Hint: "first X, then Y" reads naturally with andThen.
        throw new UnsupportedOperationException("TODO: Exercise 4 - vatThenRounded");
    }

    /**
     * Rounds first, then adds VAT.
     *
     * <p>Example: {@code 10.4} becomes {@code 12.5} (rounded 10.0, then * 1.25 = 12.5).
     * Same two functions as {@link #vatThenRounded()}, different order, different answer.
     */
    public static Function<Double, Double> roundedThenVat() {
        // TODO: combine ROUND and ADD_VAT so that rounding happens first
        // Hint: which function do you call andThen ON decides which runs first.
        throw new UnsupportedOperationException("TODO: Exercise 4 - roundedThenVat");
    }

    /**
     * A receipt formatter that adds VAT and then formats the result.
     * Build it with {@code compose}, calling it on {@code FORMAT_NOK}.
     *
     * <p>Example: {@code 10.0} becomes {@code "NOK 12.5"}.
     */
    public static Function<Double, String> formatWithVat() {
        // TODO: use FORMAT_NOK.compose(...) so VAT is added before formatting
        // Hint: f.compose(g) runs g FIRST and passes its result into f. Check that the types line up.
        throw new UnsupportedOperationException("TODO: Exercise 4 - formatWithVat");
    }
}
