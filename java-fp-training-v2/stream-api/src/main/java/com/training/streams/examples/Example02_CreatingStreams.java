package com.training.streams.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Example 02 - Creating streams.
 *
 * <p>Idea: every pipeline starts with a SOURCE. Here are the common ways to get one.
 * (Reading a file with Files.lines has its own example: Example03_FilesLines.)
 */
public class Example02_CreatingStreams {

    public static void main(String[] args) {
        // From any Collection (List, Set, ...): the most common source in backend code.
        List<String> cities = List.of("Oslo", "Bergen", "Tromso");
        System.out.println("collection.stream()      : " + cities.stream().toList());
        // prints [Oslo, Bergen, Tromso]

        // From a few fixed values.
        System.out.println("Stream.of(...)           : " + Stream.of("a", "b", "c").toList());
        // prints [a, b, c]

        // From an array.
        int[] scores = {7, 3, 9};
        System.out.println("Arrays.stream(int[])     : " + Arrays.toString(Arrays.stream(scores).toArray()));
        // prints [7, 3, 9]   (an IntStream of primitive ints)

        // Number ranges. range excludes the end, rangeClosed includes it.
        System.out.println("IntStream.range(1, 5)    : " + IntStream.range(1, 5).boxed().toList());
        // prints [1, 2, 3, 4]
        System.out.println("IntStream.rangeClosed(1,5): " + IntStream.rangeClosed(1, 5).boxed().toList());
        // prints [1, 2, 3, 4, 5]

        // Stream.iterate(seed, next) is INFINITE: 1, 2, 4, 8, ... forever. limit() makes it finite.
        System.out.println("iterate + limit          : " + Stream.iterate(1, x -> x * 2).limit(6).toList());
        // prints [1, 2, 4, 8, 16, 32]

        // Java 9: iterate(seed, hasNext, next) works like a for loop and stops by itself.
        System.out.println("iterate(seed, test, next): " + Stream.iterate(10, x -> x > 0, x -> x - 3).toList());
        // prints [10, 7, 4, 1]

        // Stream.generate(supplier) calls a Supplier (Topic 2) again and again. Also infinite.
        Random random = new Random(42); // fixed seed so the output is the same every run
        System.out.println("generate + limit         : "
                + Stream.generate(() -> random.nextInt(100)).limit(3).toList());
        // prints [30, 63, 48]   (seed 42 always gives the same numbers)

        // An empty stream: handy as a "nothing here" return value instead of null.
        System.out.println("Stream.empty()           : " + Stream.empty().toList());
        // prints []
    }
}
