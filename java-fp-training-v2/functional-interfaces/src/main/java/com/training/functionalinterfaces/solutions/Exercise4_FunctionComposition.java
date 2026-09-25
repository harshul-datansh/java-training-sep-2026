package com.training.functionalinterfaces.solutions;

import java.util.Locale;
import java.util.function.Function;

/**
 * Solution to Exercise 4 - Function composition with andThen and compose.
 */
public class Exercise4_FunctionComposition {

    // ---- Given building blocks (identical to the exercise) --------------------

    public static final Function<String, String> TRIM = String::trim;
    public static final Function<String, String> LOWERCASE = s -> s.toLowerCase(Locale.ROOT);
    public static final Function<String, String> SPACES_TO_HYPHENS = s -> s.replaceAll("\\s+", "-");
    public static final Function<String, String> STRIP_INVALID = s -> s.replaceAll("[^a-z0-9-]", "");
    public static final Function<Double, Double> ADD_VAT = price -> price * 1.25;
    public static final Function<Double, Double> ROUND = price -> (double) Math.round(price);
    public static final Function<Double, String> FORMAT_NOK = price -> "NOK " + price;

    // ---- Solutions ------------------------------------------------------------

    public static Function<String, String> slugBuilder() {
        // andThen reads left to right in execution order, so the code looks like the spec.
        // The order matters: STRIP_INVALID must come after LOWERCASE (it only keeps a-z),
        // and after SPACES_TO_HYPHENS (otherwise it would delete the spaces first
        // and "Hello World" would become "helloworld").
        return TRIM
                .andThen(LOWERCASE)
                .andThen(SPACES_TO_HYPHENS)
                .andThen(STRIP_INVALID);
    }

    public static Function<Double, Double> vatThenRounded() {
        // ADD_VAT runs first because it is the one andThen is called ON.
        return ADD_VAT.andThen(ROUND);
    }

    public static Function<Double, Double> roundedThenVat() {
        // Swap the receiver: ROUND runs first. 10.4 -> 10.0 -> 12.5.
        // Real-world lesson: "round then tax" vs "tax then round" is a genuine
        // business decision, and composition order is where it is encoded.
        return ROUND.andThen(ADD_VAT);
    }

    public static Function<Double, String> formatWithVat() {
        // f.compose(g) = x -> f(g(x)): ADD_VAT runs first, FORMAT_NOK last.
        // Types: ADD_VAT is Double -> Double, FORMAT_NOK is Double -> String,
        // so the result is Double -> String. Equivalent to ADD_VAT.andThen(FORMAT_NOK).
        return FORMAT_NOK.compose(ADD_VAT);
    }
}
