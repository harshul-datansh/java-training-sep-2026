package com.training.demo.functional.demo;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;

/**
 * DEMO 1 - What makes an interface "functional"?
 *
 * Rule: exactly ONE abstract method. That is the method a lambda implements.
 * These do NOT count toward the one:
 *   - default methods (they already have a body)
 *   - static methods  (they belong to the interface, not to the object)
 *   - public methods of Object redeclared in the interface (equals, hashCode, toString),
 *     because every object already has an implementation of them
 *
 * To see the compiler errors when the rule is broken, run the files in broken-examples/
 * (the README and docs/02-functional-interfaces.md show the exact commands and messages).
 */
public class Demo01_WhatMakesItFunctional {

    @FunctionalInterface
    interface OrderCheck {
        // 1 abstract method -> this is the ONLY thing a lambda has to provide.
        boolean check(Order order);

        // default method: has a body, does not count.
        default String describe(Order order) {
            return order.id() + " -> " + (check(order) ? "OK" : "REJECTED");
        }

        // static method: does not count.
        static OrderCheck alwaysOk() {
            return order -> true;
        }

        // Redeclared Object method: does not count either.
        @Override
        String toString();
    }

    public static void main(String[] args) {
        // The lambda only implements check(...). describe(...) comes for free.
        OrderCheck notCancelled = order -> order.status() != OrderStatus.CANCELLED;

        System.out.println("=== OrderCheck declares 4 methods, but only check(...) counts as THE abstract method ===");
        for (Order o : SampleData.orders()) {
            System.out.println("  " + notCancelled.describe(o));
        }
        System.out.println();
        System.out.println("  static helper alwaysOk() on A-104 -> "
                + OrderCheck.alwaysOk().describe(SampleData.orders().get(4)));
    }
}
