package com.training.lambdas.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/** Solution to Exercise 4. */
public class Exercise4_VariableCapture {

    public static Function<Double, Double> priceWithDiscount(double percent) {
        // 'percent' is never reassigned, so it is effectively final. Java copies
        // its value into the lambda, which is why the lambda keeps working after
        // this method has returned and its local variables are gone.
        return price -> price * (1 - percent / 100);
    }

    public static List<Supplier<String>> ticketLabels(String prefix, int count) {
        List<Supplier<String>> labels = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            // 'i' changes every iteration (i++), so it is NOT effectively final
            // and cannot be captured. 'number' is a NEW variable per iteration,
            // assigned exactly once, so each lambda captures its own value.
            int number = i;
            labels.add(() -> prefix + "-" + number);
        }
        return labels;
    }

    public static Supplier<Integer> liveItemCount(List<String> cart) {
        // The rule is about the VARIABLE, not the object. 'cart' always refers
        // to the same list, so it can be captured. The list's contents may
        // change, and because size() is called inside the lambda, it is
        // evaluated at get() time and sees the current contents.
        return () -> cart.size();
        // Equivalent method reference (kind 2, particular object): cart::size
    }
}
