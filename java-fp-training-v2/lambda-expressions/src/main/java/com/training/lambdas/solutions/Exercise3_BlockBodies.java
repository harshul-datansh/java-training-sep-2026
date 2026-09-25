package com.training.lambdas.solutions;

import java.util.Comparator;
import java.util.function.Function;

/** Solution to Exercise 3. */
public class Exercise3_BlockBodies {

    public static Function<String, String> maskCardNumber() {
        // Several steps and an early exit: a block body is the right tool.
        // With braces, every path must end in "return".
        return card -> {
            String digits = card.replace(" ", "");
            if (digits.length() <= 4) {
                return digits;
            }
            int hidden = digits.length() - 4;
            return "*".repeat(hidden) + digits.substring(hidden);
        };
    }

    public static Comparator<String> byLengthThenAlphabetical() {
        // Compare the "most important" property first and only fall back to the
        // second one on a tie (result 0). Integer.compare avoids the overflow
        // risk of subtracting ints (not a problem for lengths, but a good habit).
        // Topic 2 shows a shorter way: Comparator.comparing(String::length).thenComparing(...)
        return (a, b) -> {
            int byLength = Integer.compare(a.length(), b.length());
            if (byLength != 0) {
                return byLength;
            }
            return a.compareTo(b);
        };
    }

    public static Function<Integer, String> httpStatusCategory() {
        // An if/else chain needs statements, so it needs a block.
        // The final return covers "everything else", so no path is missing a return.
        return status -> {
            if (status >= 200 && status <= 299) {
                return "SUCCESS";
            } else if (status >= 400 && status <= 499) {
                return "CLIENT_ERROR";
            } else if (status >= 500 && status <= 599) {
                return "SERVER_ERROR";
            }
            return "OTHER";
        };
    }
}
