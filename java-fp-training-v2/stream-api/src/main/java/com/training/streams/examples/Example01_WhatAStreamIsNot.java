package com.training.streams.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Example 01 - What a stream is NOT.
 *
 * <p>Idea: a stream is not a collection. It does not store elements, it does not change
 * its source, and it can be used only once.
 */
public class Example01_WhatAStreamIsNot {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("bo", "ann", "cy"));

        // 1) A stream does NOT modify its source.
        //    map(String::toUpperCase) creates NEW strings in a NEW list. 'names' stays as it was.
        List<String> upper = names.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("1) source after stream : " + names);  // prints [bo, ann, cy]
        System.out.println("1) new result list     : " + upper);  // prints [BO, ANN, CY]

        // 2) A stream does NOT store elements. It is a pipeline that pulls them from the source
        //    when a terminal operation asks. Printing a stream shows an object, not data.
        Stream<String> stream = names.stream();
        System.out.println("2) a stream is not data: " + stream.getClass().getName());
        // prints java.util.stream.ReferencePipeline$Head - an internal JDK class name, no elements.

        // 3) A stream can be used only ONCE. After a terminal operation it is "consumed".
        Stream<String> once = names.stream();
        System.out.println("3) first use  count()  : " + once.count()); // prints 3
        try {
            once.count(); // second terminal operation on the same stream
        } catch (IllegalStateException e) {
            // prints: stream has already been operated upon or closed
            System.out.println("3) second use throws   : " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // The fix is simple: ask the source for a NEW stream every time you need one.
        System.out.println("3) fresh stream count(): " + names.stream().count()); // prints 3
    }
}
