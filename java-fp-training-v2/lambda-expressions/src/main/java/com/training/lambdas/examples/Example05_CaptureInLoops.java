package com.training.lambdas.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Example 05: the loop-variable trap.
 *
 * <p>A classic {@code for (int i = 0; ...; i++)} loop has ONE variable
 * {@code i} that changes on every iteration, so it is NOT effectively final
 * and a lambda cannot capture it.
 */
public class Example05_CaptureInLoops {

    public static void main(String[] args) {
        List<Supplier<String>> labels = new ArrayList<>();

        // DOES NOT COMPILE:
        // for (int i = 1; i <= 3; i++) {
        //     labels.add(() -> "Ticket #" + i);
        //     // error: local variables referenced from a lambda expression
        //     //        must be final or effectively final
        // }

        // FIX: copy i into a NEW variable inside the loop body.
        // Each iteration creates a fresh 'number' that is assigned once,
        // so each lambda captures its own, never-changing value.
        for (int i = 1; i <= 3; i++) {
            int number = i;
            labels.add(() -> "Ticket #" + number);
        }
        for (Supplier<String> label : labels) {
            System.out.println(label.get());
        }
        // prints
        // Ticket #1
        // Ticket #2
        // Ticket #3

        // GOOD NEWS: the enhanced for loop does not have this problem.
        // 'name' is a brand new variable on every iteration, never reassigned,
        // so it is effectively final and can be captured directly.
        List<Supplier<String>> greetings = new ArrayList<>();
        for (String name : List.of("Ada", "Linus")) {
            greetings.add(() -> "Hi " + name);
        }
        greetings.forEach(g -> System.out.println(g.get()));
        // prints
        // Hi Ada
        // Hi Linus
    }
}
