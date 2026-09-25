package com.training.functionalinterfaces.examples;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Example 04 - Supplier, Consumer and BiConsumer.
 *
 * <pre>
 *   Supplier&lt;T&gt;          T get()               no input, gives a value
 *   Consumer&lt;T&gt;          void accept(T t)      takes a value, gives nothing back
 *   BiConsumer&lt;T, U&gt;     void accept(T t, U u) takes two values, gives nothing back
 * </pre>
 *
 * Supplier = "a producer". Consumer = "do something WITH this" (a side effect:
 * print it, save it, send it...).
 */
public class Example04_SupplierAndConsumer {

    public static void main(String[] args) {
        // --- Supplier: no arguments, returns something ---------------------
        Supplier<List<String>> newList = ArrayList::new;   // constructor reference
        List<String> a = newList.get();
        List<String> b = newList.get();
        // Every get() runs the code again -> two different lists.
        System.out.println("Supplier gives a new list each time? " + (a != b));
        // prints Supplier gives a new list each time? true

        Supplier<String> greeting = () -> "Hello from a Supplier";
        System.out.println(greeting.get());
        // prints Hello from a Supplier

        // --- Consumer: takes something, returns nothing -----------------------
        List<String> auditLog = new ArrayList<>();
        Consumer<String> audit = message -> auditLog.add("AUDIT: " + message);
        audit.accept("order ORD-1 created");
        audit.accept("order ORD-1 paid");
        System.out.println("Audit log: " + auditLog);
        // prints Audit log: [AUDIT: order ORD-1 created, AUDIT: order ORD-1 paid]

        // System.out::println is the most famous Consumer<String> of all.
        Consumer<String> printer = System.out::println;
        printer.accept("Printed by a Consumer");
        // prints Printed by a Consumer

        // --- BiConsumer: takes two things, returns nothing --------------------
        // Map.forEach takes a BiConsumer<K, V>: it calls it once per entry.
        Map<String, Integer> stock = new LinkedHashMap<>();
        stock.put("SKU-0001", 12);
        stock.put("SKU-0002", 0);

        BiConsumer<String, Integer> printStock =
                (sku, qty) -> System.out.println("  " + sku + " -> " + qty + " in stock");
        System.out.println("Stock report:");
        stock.forEach(printStock);
        // prints   SKU-0001 -> 12 in stock
        // prints   SKU-0002 -> 0 in stock
    }
}
