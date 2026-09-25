package com.training.lambdas.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Example 01: the problem lambdas solve.
 *
 * <p>We want to sort a list of words by length. Sorting needs a piece of
 * BEHAVIOUR ("how do I compare two words?"). Before Java 8 the only way to
 * hand behaviour to a method was to wrap it in an object, usually an
 * anonymous inner class. A lambda is a much shorter way to write that same
 * object.
 */
public class Example01_AnonymousClassVsLambda {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("banana", "fig", "apple"));

        // ------------------------------------------------------------
        // BEFORE (pre-Java 8): an anonymous inner class.
        // ------------------------------------------------------------
        // We create an object of an unnamed class that implements Comparator.
        // Five lines of ceremony wrap ONE line that actually matters:
        //     return Integer.compare(a.length(), b.length());
        Comparator<String> byLengthOldStyle = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Integer.compare(a.length(), b.length());
            }
        };
        words.sort(byLengthOldStyle);
        System.out.println("Anonymous class: " + words); // prints [fig, apple, banana]

        // ------------------------------------------------------------
        // AFTER (Java 8+): a lambda.
        // ------------------------------------------------------------
        // Comparator has exactly ONE abstract method: compare(a, b).
        // So the compiler already knows:
        //   - which method we are implementing (compare)
        //   - the parameter types (String, String) from Comparator<String>
        //   - the return type (int)
        // All we write is the part that differs: parameters -> body.
        Comparator<String> byLength = (a, b) -> Integer.compare(a.length(), b.length());

        words = new ArrayList<>(List.of("banana", "fig", "apple"));
        words.sort(byLength);
        System.out.println("Lambda:          " + words); // prints [fig, apple, banana]

        // You don't even need the variable; pass the lambda straight in:
        words.sort((a, b) -> Integer.compare(b.length(), a.length()));
        System.out.println("Longest first:   " + words); // prints [banana, apple, fig]

        // ------------------------------------------------------------
        // Same idea with Runnable (a task with no input and no result).
        // ------------------------------------------------------------
        Runnable oldTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from an anonymous class");
            }
        };
        Runnable newTask = () -> System.out.println("Hello from a lambda");

        oldTask.run();
        newTask.run();

        // KEY TAKEAWAY: a lambda is not a new kind of thing. It is a short way
        // to create an object that implements an interface with ONE abstract
        // method. Topic 2 (functional interfaces) explores that rule in depth.
    }
}
