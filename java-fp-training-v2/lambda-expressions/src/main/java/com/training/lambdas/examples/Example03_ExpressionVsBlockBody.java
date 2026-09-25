package com.training.lambdas.examples;

import java.util.function.Function;

/**
 * Example 03: the BODY part of a lambda.
 *
 * <pre>
 *   (parameters)  -&gt;  body
 *                     ^^^^
 *                     this file
 * </pre>
 *
 * There are two body styles:
 * <ul>
 *   <li>EXPRESSION body: no braces. The value of the expression IS the result.
 *       No {@code return} keyword allowed.</li>
 *   <li>BLOCK body: braces. Works like a normal method body: statements,
 *       local variables, if/else, and an explicit {@code return}.</li>
 * </ul>
 */
public class Example03_ExpressionVsBlockBody {

    public static void main(String[] args) {
        // EXPRESSION body: one expression, result returned automatically.
        Function<Integer, Integer> square = x -> x * x;
        System.out.println("expression body: " + square.apply(5)); // prints 25

        // BLOCK body: exactly the same result, written the long way.
        Function<Integer, Integer> squareBlock = x -> {
            int result = x * x;
            return result;          // with braces, return is REQUIRED
        };
        System.out.println("block body:      " + squareBlock.apply(5)); // prints 25

        // A block body earns its place when you need several steps.
        // Here: hide most of an email address before logging it.
        Function<String, String> maskEmail = email -> {
            int at = email.indexOf('@');
            if (at <= 1) {
                return email;       // too short to mask, leave it alone
            }
            return email.charAt(0) + "*".repeat(at - 1) + email.substring(at);
        };
        System.out.println("masked:          " + maskEmail.apply("harshul@example.com"));
        // prints h******@example.com

        // Rules of thumb:
        //   - If it fits in one readable expression, use an expression body.
        //   - If you need a block, keep it SHORT (a few lines).
        //   - If it grows beyond that, move it into a named method
        //     (see Example10_ExtractComplexLambda).

        // Mistakes that do NOT compile (real javac messages):
        //
        // Function<Integer, Integer> a = x -> { x * x; };
        //   error: not a statement
        //   (inside braces you need statements, and "x * x;" is not one)
        //
        // Function<Integer, Integer> b = x -> { int y = x * x; };
        //   error: incompatible types: bad return type in lambda expression
        //       missing return value
        //
        // Function<Integer, Integer> c = x -> return x * x;
        //   does not compile: "return" is only allowed inside braces
    }
}
