package com.training.demo.streams.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.util.List;
import java.util.stream.Stream;

/**
 * DEMO 1 - What a stream IS and what it is NOT.
 *
 * A stream is a PIPELINE that data flows through, not a container that holds data.
 *   - It does not store elements (the List does).
 *   - It does not change its source (filter does not remove anything from the List).
 *   - It can be used only ONCE.
 */
public class Demo01_WhatIsAStream {

    public static void main(String[] args) {
        List<Order> orders = SampleData.orders();

        // BEFORE: a loop that says HOW to do it, step by step.
        int paidLoop = 0;
        for (Order o : orders) {
            if (o.isPaid()) {
                paidLoop++;
            }
        }

        // AFTER: a stream that says WHAT we want.
        long paidStream = orders.stream()      // source
                .filter(Order::isPaid)         // intermediate: keep only paid orders
                .count();                      // terminal: produce the result

        System.out.println("=== Same answer, two styles ===");
        System.out.println("  loop   : " + paidLoop + " paid orders");
        System.out.println("  stream : " + paidStream + " paid orders");
        System.out.println();

        // NOT a data structure: the source list is untouched after filter.
        System.out.println("=== The source is not modified ===");
        System.out.println("  list size after filtering: " + orders.size() + " (still all orders)");
        System.out.println();

        // SINGLE USE: a stream is consumed by its terminal operation.
        Stream<Order> stream = orders.stream().filter(Order::premium);
        System.out.println("=== A stream can be used only once ===");
        System.out.println("  first use : " + stream.count() + " premium orders");
        try {
            System.out.println("  second use: " + stream.count());
        } catch (IllegalStateException e) {
            System.out.println("  second use: IllegalStateException -> " + e.getMessage());
        }
        System.out.println("  fix: ask the SOURCE for a new stream each time -> "
                + orders.stream().filter(Order::premium).count() + " premium orders");
    }
}
