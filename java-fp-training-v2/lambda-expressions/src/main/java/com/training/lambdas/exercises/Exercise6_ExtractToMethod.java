package com.training.lambdas.exercises;

import java.util.List;
import java.util.function.Predicate;

/**
 * Exercise 6 (medium-hard): don't cram logic into a lambda, extract it.
 *
 * <p>Security wants a password rule. A strong password:
 * <ul>
 *   <li>is not null and has at least 8 characters</li>
 *   <li>contains at least one upper-case letter</li>
 *   <li>contains at least one digit</li>
 *   <li>contains no whitespace</li>
 * </ul>
 * Examples: {@code "Secret123"} strong, {@code "secret123"} weak (no upper case),
 * {@code "Secret 123"} weak (space), {@code "Sh0rt"} weak (too short).
 *
 * <p>Writing all of that inside a lambda works, but nobody can read it or test
 * it on its own. So: put the logic in a normal named method, and make the
 * Predicate a method reference to it. See Example10_ExtractComplexLambda.
 */
public class Exercise6_ExtractToMethod {

    /** The rule itself, as a plain method. */
    public static boolean isStrongPassword(String password) {
        // TODO: implement the four rules above.
        // Hint: check null/length first and return early. A for loop over
        //       password.toCharArray() with Character.isUpperCase, Character.isDigit
        //       and Character.isWhitespace covers the rest.
        throw new UnsupportedOperationException("TODO: Exercise 6 - isStrongPassword");
    }

    /** The same rule as a Predicate, so it can be passed around. */
    public static Predicate<String> strongPasswordRule() {
        // TODO: return a method reference to isStrongPassword.
        // Hint: which of the four kinds is a reference to a static method?
        throw new UnsupportedOperationException("TODO: Exercise 6 - strongPasswordRule");
    }

    /**
     * Returns the weak passwords from the input, in their original order.
     * Must use {@code strongPasswordRule()}.
     */
    public static List<String> weakPasswords(List<String> passwords) {
        // TODO: loop over the list and keep the ones that FAIL the rule.
        // Hint: get the Predicate once, then call its test(...) method in the loop.
        //       new ArrayList<>() to collect results.
        throw new UnsupportedOperationException("TODO: Exercise 6 - weakPasswords");
    }
}
