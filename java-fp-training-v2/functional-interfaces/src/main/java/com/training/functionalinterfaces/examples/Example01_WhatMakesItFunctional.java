package com.training.functionalinterfaces.examples;

import java.util.Comparator;
import java.util.Locale;

/**
 * Example 01 - What makes an interface "functional"?
 *
 * <p>Rule: a functional interface has EXACTLY ONE abstract method.
 * That single method is the "slot" a lambda fills in.
 *
 * <p>Things that do NOT count towards the "one abstract method" rule:
 * <ul>
 *   <li>default methods (they already have a body)</li>
 *   <li>static methods (they already have a body)</li>
 *   <li>abstract re-declarations of public methods from java.lang.Object,
 *       such as {@code boolean equals(Object o)} - every object already
 *       has an implementation of those, inherited from Object</li>
 * </ul>
 */
public class Example01_WhatMakesItFunctional {

    // One abstract method -> functional. A lambda can implement it.
    interface Greeter {
        String greet(String name);
    }

    // Still ONE abstract method. The default and static methods have bodies,
    // so they are not "holes" the lambda has to fill.
    interface PriceFormatter {
        String format(double price);                 // the one abstract method

        default String formatWithCurrency(double price) {
            return format(price) + " NOK";           // re-uses the abstract method
        }

        static PriceFormatter twoDecimals() {
            return price -> String.format(Locale.ROOT, "%.2f", price);
        }
    }

    // Still ONE abstract method: equals(Object) is a public method of Object,
    // so re-declaring it here does not count. java.util.Comparator does exactly
    // this (it declares both compare(...) and equals(...)).
    interface SkuMatcher {
        boolean matches(String sku);

        boolean equals(Object other);                // does NOT count
    }

    public static void main(String[] args) {
        // Each lambda below becomes the implementation of that ONE abstract method.
        Greeter greeter = name -> "Hello, " + name;
        System.out.println("Greeter:          " + greeter.greet("Kari"));
        // prints Greeter:          Hello, Kari

        PriceFormatter plain = price -> "" + price;
        System.out.println("Plain formatter:  " + plain.formatWithCurrency(99.5));
        // prints Plain formatter:  99.5 NOK

        PriceFormatter twoDp = PriceFormatter.twoDecimals();
        System.out.println("Static factory:   " + twoDp.formatWithCurrency(99.5));
        // prints Static factory:   99.50 NOK

        SkuMatcher startsWithSku = sku -> sku.startsWith("SKU-");
        System.out.println("SkuMatcher:       " + startsWithSku.matches("SKU-1234"));
        // prints SkuMatcher:       true

        // The JDK's own Comparator: compare(...) is abstract, equals(...) is also
        // declared abstract, and it has lots of default/static methods.
        // Still functional, so a lambda works:
        Comparator<String> byLength = (a, b) -> Integer.compare(a.length(), b.length());
        System.out.println("Comparator:       " + byLength.compare("abc", "abcdef"));
        // prints Comparator:       -1
    }
}
