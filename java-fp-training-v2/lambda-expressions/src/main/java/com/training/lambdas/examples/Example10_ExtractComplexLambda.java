package com.training.lambdas.examples;

import java.util.List;
import java.util.function.Predicate;

/**
 * Example 10: common mistake, a lambda body that grew too big.
 *
 * <p>Lambdas are meant to be short. When a lambda needs several lines,
 * if/else branches or comments to be understood, give that logic a NAME by
 * moving it into a method, then refer to the method.
 */
public class Example10_ExtractComplexLambda {

    public static void main(String[] args) {
        List<String> usernames = List.of("ada_l", "x", "9lives", "Linus", "grace_h");

        // ------------------------------------------------------------
        // BEFORE: everything crammed into the lambda.
        // ------------------------------------------------------------
        // Hard to read, impossible to unit test on its own, can't be reused.
        Predicate<String> validInline = name -> {
            if (name == null || name.length() < 3 || name.length() > 16) {
                return false;
            }
            if (Character.isDigit(name.charAt(0))) {
                return false;
            }
            for (char c : name.toCharArray()) {
                if (!(Character.isLowerCase(c) || Character.isDigit(c) || c == '_')) {
                    return false;
                }
            }
            return true;
        };

        // ------------------------------------------------------------
        // AFTER: the logic has a name; the lambda becomes a method reference.
        // ------------------------------------------------------------
        Predicate<String> valid = Example10_ExtractComplexLambda::isValidUsername;

        for (String name : usernames) {
            System.out.println(name + " -> inline=" + validInline.test(name)
                    + ", extracted=" + valid.test(name));
        }
        // prints
        // ada_l -> inline=true, extracted=true
        // x -> inline=false, extracted=false
        // 9lives -> inline=false, extracted=false
        // Linus -> inline=false, extracted=false
        // grace_h -> inline=true, extracted=true

        // Benefits of the extracted version:
        //   - the call site reads like a sentence: filter(isValidUsername)
        //   - isValidUsername can have its own unit tests and Javadoc
        //   - stack traces show a real method name instead of lambda$main$0
    }

    /** 3-16 chars, lowercase letters/digits/underscore, must not start with a digit. */
    static boolean isValidUsername(String name) {
        if (name == null || name.length() < 3 || name.length() > 16) {
            return false;
        }
        if (Character.isDigit(name.charAt(0))) {
            return false;
        }
        return name.chars().allMatch(c -> Character.isLowerCase(c) || Character.isDigit(c) || c == '_');
    }
}
