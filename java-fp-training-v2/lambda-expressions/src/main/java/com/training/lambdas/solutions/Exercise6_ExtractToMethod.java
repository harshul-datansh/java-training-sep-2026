package com.training.lambdas.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** Solution to Exercise 6. */
public class Exercise6_ExtractToMethod {

    public static boolean isStrongPassword(String password) {
        // A normal method: it has a name, can be unit tested alone, and can
        // use early returns and loops without making anyone read a giant lambda.
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isWhitespace(c)) {
                return false;
            }
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        return hasUpper && hasDigit;
    }

    public static Predicate<String> strongPasswordRule() {
        // Kind 1 method reference (static method). The call site now reads
        // like a sentence and the rule lives in exactly one place.
        return Exercise6_ExtractToMethod::isStrongPassword;
    }

    public static List<String> weakPasswords(List<String> passwords) {
        // The Predicate is just an object: fetch it once, call test() in a loop.
        // (Topic 3 replaces this loop with passwords.stream().filter(...).)
        Predicate<String> rule = strongPasswordRule();
        List<String> weak = new ArrayList<>();
        for (String password : passwords) {
            if (!rule.test(password)) {
                weak.add(password);
            }
        }
        return weak;
    }
}
