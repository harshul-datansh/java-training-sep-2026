package com.training.demo.streams.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.util.List;
import java.util.stream.Stream;

/**
 * DEMO 4 - Streams are LAZY.
 *
 *  1. Intermediate ops only DESCRIBE the work. Nothing runs until a terminal op asks for results.
 *  2. Elements flow through the whole pipeline ONE AT A TIME (not "filter everything, then map everything").
 *  3. Short-circuiting ops like limit(n) stop pulling elements as soon as they have enough.
 */
public class Demo04_Laziness {

    public static void main(String[] args) {
        List<Order> orders = SampleData.orders();

        System.out.println("=== 1) No terminal operation: nothing runs ===");
        Stream<String> described = orders.stream()
                .filter(o -> {
                    System.out.println("    filter " + o.id());
                    return o.total() > 100;
                })
                .map(o -> {
                    System.out.println("    map    " + o.id());
                    return o.id();
                });
        System.out.println("  pipeline built and stored in a variable - but no filter/map line was printed above.");
        System.out.println();

        System.out.println("=== 2) With a terminal operation: one element at a time ===");
        List<String> result = orders.stream()
                .filter(o -> {
                    System.out.println("    filter " + o.id() + " (total " + o.total() + ")");
                    return o.total() > 100;
                })
                .map(o -> {
                    System.out.println("    map    " + o.id() + "   <- passed the filter, goes straight on");
                    return o.id();
                })
                .toList();
        System.out.println("  result: " + result);
        System.out.println();

        System.out.println("=== 3) limit(2) short-circuits: stops after 2 matches ===");
        List<String> firstTwo = orders.stream()
                .filter(o -> {
                    System.out.println("    filter " + o.id());
                    return o.total() > 100;
                })
                .map(Order::id)
                .limit(2)
                .toList();
        System.out.println("  result: " + firstTwo + "   (A-103 .. A-106 were never looked at)");
    }
}
