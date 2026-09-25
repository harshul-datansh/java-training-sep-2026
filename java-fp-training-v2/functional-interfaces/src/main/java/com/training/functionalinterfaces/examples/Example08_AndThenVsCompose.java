package com.training.functionalinterfaces.examples;

import java.util.function.Function;

/**
 * Example 08 - Function.andThen vs Function.compose: same two functions, different order.
 *
 * <pre>
 *   f.andThen(g)   means  x -> g(f(x))   "first f, THEN g"     (reads left to right)
 *   f.compose(g)   means  x -> f(g(x))   "g first, then f"     (reads like maths: f o g)
 * </pre>
 *
 * Both are DEFAULT methods on Function. They do not run anything yet - they
 * return a NEW Function. The work only happens when you call apply(...).
 */
public class Example08_AndThenVsCompose {

    public static void main(String[] args) {
        // Each function prints a trace line so we can SEE the order of execution.
        Function<Integer, Integer> times2 = x -> {
            System.out.println("    times2(" + x + ") = " + (x * 2));
            return x * 2;
        };
        Function<Integer, Integer> plus3 = x -> {
            System.out.println("    plus3(" + x + ")  = " + (x + 3));
            return x + 3;
        };

        // Building the combined function prints NOTHING: nothing runs yet.
        Function<Integer, Integer> andThen = times2.andThen(plus3);
        Function<Integer, Integer> compose = times2.compose(plus3);
        System.out.println("Built both functions (nothing has run yet)");
        // prints Built both functions (nothing has run yet)

        System.out.println("times2.andThen(plus3).apply(5):");
        int r1 = andThen.apply(5);
        // prints     times2(5) = 10
        // prints     plus3(10)  = 13
        System.out.println("  result = " + r1);
        // prints   result = 13

        System.out.println("times2.compose(plus3).apply(5):");
        int r2 = compose.apply(5);
        // prints     plus3(5)  = 8
        // prints     times2(8) = 16
        System.out.println("  result = " + r2);
        // prints   result = 16

        // Tip: if in doubt, use andThen. It reads in the order things happen.
        // plus3.andThen(times2) is exactly the same as times2.compose(plus3):
        System.out.println("plus3.andThen(times2).apply(5):");
        int r3 = plus3.andThen(times2).apply(5);
        // prints     plus3(5)  = 8
        // prints     times2(8) = 16
        System.out.println("  result = " + r3);
        // prints   result = 16
    }
}
