package com.training.streams.examples;

import com.training.streams.model.Product;
import com.training.streams.model.SampleData;

import java.util.ArrayList;
import java.util.List;

/**
 * Example 04 - filter and map.
 *
 * <p>Idea: {@code filter(Predicate)} keeps the elements that pass a test,
 * {@code map(Function)} turns each element into something else. The same task is shown
 * with a classic loop first, so you can compare.
 */
public class Example04_FilterAndMap {

    public static void main(String[] args) {
        List<Product> products = SampleData.products();

        // Task: names of electronics products that are in stock.

        // BEFORE: loop + if + add to a temporary list.
        List<String> loopResult = new ArrayList<>();
        for (Product p : products) {
            if (p.category().equals("electronics") && p.inStock()) {
                loopResult.add(p.name());
            }
        }
        System.out.println("loop   : " + loopResult);
        // prints [Mechanical Keyboard, Wireless Mouse]

        // AFTER: the same logic as a pipeline. Read it top to bottom like a sentence.
        List<String> streamResult = products.stream()                 // Stream<Product>
                .filter(p -> p.category().equals("electronics"))      // still Stream<Product>, fewer elements
                .filter(Product::inStock)                             // still Stream<Product>
                .map(Product::name)                                   // now Stream<String>
                .toList();
        System.out.println("stream : " + streamResult);
        // prints [Mechanical Keyboard, Wireless Mouse]

        // map can change the TYPE of the elements. Here Product -> Double -> String.
        List<String> priceLabels = products.stream()
                .filter(p -> p.price() > 200)
                .map(Product::price)                    // Stream<Double>
                .map(price -> price + " NOK")           // Stream<String>
                .toList();
        System.out.println("labels : " + priceLabels);
        // prints [360.0 NOK, 480.0 NOK, 240.0 NOK]
    }
}
