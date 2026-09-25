package com.training.streams.examples;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Example 09 - Collecting to a List: Stream.toList() vs Collectors.toList().
 *
 * <p>Idea: both give you a List, but only one of them may be changed afterwards.
 * <ul>
 *   <li>{@code stream.toList()} (Java 16+) returns an UNMODIFIABLE list.</li>
 *   <li>{@code stream.collect(Collectors.toList())} returns a list that is in practice an
 *       ArrayList today, but the Javadoc gives no guarantee about its type or mutability.
 *       If you need a list you will change, say so: {@code Collectors.toCollection(ArrayList::new)}.</li>
 * </ul>
 */
public class Example09_CollectToList {

    public static void main(String[] args) {
        // Java 16+: short, and the result cannot be modified.
        List<String> fixed = Stream.of("a", "b").toList();
        System.out.println("Stream.toList()      : " + fixed); // prints [a, b]
        try {
            fixed.add("c");
        } catch (UnsupportedOperationException e) {
            // The exception has no message (getMessage() is null), so we print the class name.
            System.out.println("add() on toList()    : throws " + e.getClass().getSimpleName());
        }

        // Collectors.toList(): the classic Java 8 way.
        List<String> classic = Stream.of("a", "b").collect(Collectors.toList());
        classic.add("c"); // works on the current JDK, but is not promised by the API
        System.out.println("Collectors.toList()  : " + classic); // prints [a, b, c]

        // When you really need to add or remove later, be explicit about the type.
        List<String> mutable = Stream.of("a", "b").collect(Collectors.toCollection(java.util.ArrayList::new));
        mutable.add("c");
        System.out.println("toCollection(ArrayList::new): " + mutable); // prints [a, b, c]
    }
}
