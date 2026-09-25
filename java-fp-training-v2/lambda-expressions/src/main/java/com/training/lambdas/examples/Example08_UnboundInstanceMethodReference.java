package com.training.lambdas.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Example 08: method reference kind 3 of 4, an instance method on an
 * ARBITRARY object of a type. Also called an "unbound" reference.
 *
 * <pre>
 *   lambda:            s -&gt; s.toUpperCase()
 *   method reference:  String::toUpperCase
 * </pre>
 *
 * This looks like the static form ({@code ClassName::method}) but the method is
 * an INSTANCE method. There is no object yet when we write it, so the FIRST
 * lambda parameter becomes the object the method is called on.
 */
public class Example08_UnboundInstanceMethodReference {

    public static void main(String[] args) {
        // One parameter: it becomes the object.  s -> s.toUpperCase()
        Function<String, String> upper = String::toUpperCase;
        System.out.println(upper.apply("kafka")); // prints KAFKA

        // Also one parameter:  s -> s.length()
        Function<String, Integer> length = String::length;
        System.out.println(length.apply("spring")); // prints 6

        // Two parameters: the FIRST becomes the object, the SECOND the argument.
        //   (a, b) -> a.compareToIgnoreCase(b)
        Comparator<String> ignoreCase = String::compareToIgnoreCase;
        List<String> names = new ArrayList<>(List.of("bob", "Alice", "carol"));
        names.sort(ignoreCase);
        System.out.println(names); // prints [Alice, bob, carol]

        // HOW TO TELL KIND 2 AND KIND 3 APART:
        //   left side of :: is a VARIABLE/object  -> kind 2 (bound):   myList::add
        //   left side of :: is a CLASS NAME and the
        //   method is NOT static                  -> kind 3 (unbound): String::trim
    }
}
