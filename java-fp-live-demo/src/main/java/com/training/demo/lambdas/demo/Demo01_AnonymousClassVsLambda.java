package com.training.demo.lambdas.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * DEMO 1 - Why lambdas exist.
 *
 * Task: sort orders by total, cheapest first.
 * List.sort(...) needs a Comparator: "an object that knows how to compare two orders".
 * Before Java 8 the shortest way to create that object was an anonymous inner class.
 */
public class Demo01_AnonymousClassVsLambda {

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>(SampleData.orders());
        print("UNSORTED (as stored)", orders);

        // ---------------- BEFORE: anonymous inner class ----------------
        // We create a class with no name, implement its one method, and
        // create an object of it - all in one go. 6 lines of ceremony
        // for ONE line of real logic (the Double.compare call).
        Comparator<Order> byTotalOld = new Comparator<Order>() {
            @Override
            public int compare(Order a, Order b) {
                return Double.compare(a.total(), b.total());
            }
        };
        orders.sort(byTotalOld);
        print("BEFORE (anonymous class)", orders);

        // ---------------- AFTER: lambda expression ----------------
        // Same behaviour. Read it as: "given a and b, return Double.compare(...)".
        // The compiler knows the target is a Comparator<Order>, so it knows
        //   - which method we are implementing (compare)
        //   - that a and b are Orders
        //   - that we must return an int
        Comparator<Order> byTotalNew = (a, b) -> Double.compare(a.total(), b.total());

        orders = new ArrayList<>(SampleData.orders()); // reset to the original order
        orders.sort(byTotalNew);
        print("AFTER  (lambda)", orders);
    }

    private static void print(String label, List<Order> orders) {
        System.out.println("=== " + label + " ===");
        for (Order o : orders) {
            System.out.printf("  %s  %8.2f%n", o.id(), o.total());
        }
        System.out.println();
    }
}
