package com.training.lambdas.exercises;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Exercise 4 (medium): capturing variables, and the loop-variable trap.
 *
 * <p>Read Example04_EffectivelyFinal and Example05_CaptureInLoops first.
 */
public class Exercise4_VariableCapture {

    /**
     * Returns a function that applies a percentage discount.
     * {@code priceWithDiscount(20).apply(100.0)} returns {@code 80.0}.
     */
    public static Function<Double, Double> priceWithDiscount(double percent) {
        // TODO: return a lambda that uses the 'percent' parameter.
        // Hint: a method parameter that is never reassigned can be captured.
        throw new UnsupportedOperationException("TODO: Exercise 4 - priceWithDiscount");
    }

    /**
     * Returns {@code count} suppliers. Supplier number k (1-based) returns
     * {@code prefix + "-" + k}.
     * {@code ticketLabels("A", 3)} gives suppliers returning "A-1", "A-2", "A-3".
     *
     * <p>Use a classic {@code for (int i = 1; i <= count; i++)} loop.
     * Return an empty list when count is 0.
     */
    public static List<Supplier<String>> ticketLabels(String prefix, int count) {
        // TODO: build the list in a classic for loop and return it.
        // Hint: the compiler will refuse to let the lambda use 'i' directly.
        //       Why? What could you capture instead that never changes?
        throw new UnsupportedOperationException("TODO: Exercise 4 - ticketLabels");
    }

    /**
     * Returns a supplier that reports how many items are in {@code cart}
     * at the moment {@code get()} is CALLED (not when the supplier was created).
     * Example: create it for an empty list, add 2 items, get() returns 2.
     */
    public static Supplier<Integer> liveItemCount(List<String> cart) {
        // TODO: return a lambda that captures 'cart'.
        // Hint: the variable 'cart' never changes (it always points to the same
        //       list object), even though the list's CONTENTS do.
        throw new UnsupportedOperationException("TODO: Exercise 4 - liveItemCount");
    }
}
