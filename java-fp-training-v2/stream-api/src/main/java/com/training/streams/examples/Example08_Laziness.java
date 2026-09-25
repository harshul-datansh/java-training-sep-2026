package com.training.streams.examples;

import java.util.List;
import java.util.stream.Stream;

/**
 * Example 08 - Laziness.
 *
 * <p>Idea: intermediate operations only DESCRIBE the work. Nothing runs until a terminal
 * operation pulls elements through the pipeline, ONE ELEMENT AT A TIME, and operations such as
 * {@code limit} and {@code anyMatch} stop pulling as soon as they have their answer.
 *
 * <p>The println calls inside the lambdas are only there to make the order visible.
 * In real code keep lambdas free of side effects.
 */
public class Example08_Laziness {

    public static void main(String[] args) {
        List<String> names = List.of("ann", "bob", "cy", "dave", "eve");

        // 1) Building the pipeline runs NOTHING.
        System.out.println("--- 1) build the pipeline, no terminal operation ---");
        Stream<String> pipeline = names.stream()
                .filter(n -> {
                    System.out.println("  filter " + n);
                    return n.length() == 3;
                })
                .map(n -> {
                    System.out.println("  map    " + n);
                    return n.toUpperCase();
                });
        System.out.println("  (nothing printed above: filter and map have not run yet)");

        // 2) The terminal operation pulls elements through, one at a time.
        //    Note the order: filter ann, map ann, filter bob, map bob, ...
        //    NOT "filter everything first, then map everything".
        //    limit(2) stops the pull after two results: dave and eve are never even filtered.
        System.out.println("--- 2) add limit(2) and a terminal operation ---");
        List<String> result = pipeline.limit(2).toList();
        System.out.println("  result: " + result);

        // 3) anyMatch short-circuits: it stops at the first element that matches.
        System.out.println("--- 3) anyMatch stops at the first match ---");
        boolean found = names.stream()
                .peek(n -> System.out.println("  checking " + n)) // peek = look, for debugging only
                .anyMatch(n -> n.startsWith("c"));
        System.out.println("  found: " + found);
    }
}
