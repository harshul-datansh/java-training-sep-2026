package com.training.functionalinterfaces.examples;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Example 06 - UnaryOperator and BinaryOperator: "same type in, same type out".
 *
 * <pre>
 *   UnaryOperator&lt;T&gt;  extends Function&lt;T, T&gt;       T apply(T t)
 *   BinaryOperator&lt;T&gt; extends BiFunction&lt;T, T, T&gt;  T apply(T a, T b)
 * </pre>
 *
 * They add no new abstract method. They are just shorter names for the
 * common case where input and output types are the same.
 */
public class Example06_UnaryAndBinaryOperator {

    public static void main(String[] args) {
        // These two lines mean the same thing. The first is just shorter.
        UnaryOperator<String> trim1 = String::trim;
        Function<String, String> trim2 = String::trim;
        System.out.println("UnaryOperator trim: [" + trim1.apply("  hi  ") + "]");
        // prints UnaryOperator trim: [hi]
        System.out.println("Function trim:      [" + trim2.apply("  hi  ") + "]");
        // prints Function trim:      [hi]

        // Because UnaryOperator<T> IS-A Function<T, T>, you can pass it
        // wherever a Function<T, T> is expected:
        Function<String, String> asFunction = trim1;
        System.out.println("Used as Function:   [" + asFunction.apply(" x ") + "]");
        // prints Used as Function:   [x]

        // BinaryOperator: two values of the same type -> one value of that type.
        BinaryOperator<Integer> sum = Integer::sum;
        System.out.println("sum(3, 4)        = " + sum.apply(3, 4));
        // prints sum(3, 4)        = 7

        // Handy static helpers on BinaryOperator: minBy / maxBy with a Comparator.
        BinaryOperator<String> longer = BinaryOperator.maxBy(
                (a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("longer(ab, abc)  = " + longer.apply("ab", "abc"));
        // prints longer(ab, abc)  = abc

        // And a BinaryOperator IS-A BiFunction<T, T, T>.
        BiFunction<Integer, Integer, Integer> asBiFunction = sum;
        System.out.println("as BiFunction    = " + asBiFunction.apply(10, 5));
        // prints as BiFunction    = 15
    }
}
