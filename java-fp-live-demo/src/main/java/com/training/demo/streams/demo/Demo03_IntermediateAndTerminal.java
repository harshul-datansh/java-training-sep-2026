package com.training.demo.streams.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * DEMO 3 - A pipeline is: SOURCE -> zero or more INTERMEDIATE ops -> exactly one TERMINAL op.
 *
 * Intermediate ops return a new Stream (so you can keep chaining).
 * Terminal ops return a result (or void) and END the stream.
 */
public class Demo03_IntermediateAndTerminal {

    public static void main(String[] args) {
        List<Order> orders = SampleData.orders();

        System.out.println("=== Intermediate operations (each returns a Stream) ===");
        System.out.println("filter   (premium)          : "
                + orders.stream().filter(Order::premium).map(Order::id).toList());
        System.out.println("map      (order -> email)   : "
                + orders.stream().map(Order::customerEmail).toList());
        System.out.println("flatMap  (order -> its SKUs): "
                + orders.stream().flatMap(o -> o.skus().stream()).toList());
        System.out.println("distinct (unique SKUs)      : "
                + orders.stream().flatMap(o -> o.skus().stream()).distinct().toList());
        System.out.println("sorted   (by total, desc)   : "
                + orders.stream().sorted(Comparator.comparingDouble(Order::total).reversed()).map(Order::id).toList());
        System.out.println("limit(2)                    : "
                + orders.stream().limit(2).map(Order::id).toList());
        System.out.println("skip(5)                     : "
                + orders.stream().skip(5).map(Order::id).toList());
        System.out.println();

        System.out.println("=== Terminal operations (each produces a result) ===");
        System.out.println("collect  -> List            : " + orders.stream().map(Order::id).toList());
        System.out.println("count                       : " + orders.stream().count());
        System.out.println("anyMatch (any total > 900?) : " + orders.stream().anyMatch(o -> o.total() > 900));
        // reduce: combine all elements into one, starting from an identity value (0.0).
        double revenue = orders.stream().map(Order::total).reduce(0.0, Double::sum);
        System.out.printf("reduce   (sum of totals)    : %.2f%n", revenue);
        // reduce without identity returns an Optional (the stream might be empty).
        Optional<Order> biggest = orders.stream().reduce((a, b) -> a.total() >= b.total() ? a : b);
        System.out.println("reduce   (biggest order)    : " + biggest.map(Order::id).orElse("none"));
        System.out.print("forEach  (side effect)      : ");
        orders.stream().limit(3).forEach(o -> System.out.print(o.id() + " "));
        System.out.println();
    }
}
