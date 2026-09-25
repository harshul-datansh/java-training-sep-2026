package com.training.streams.examples;

import com.training.streams.model.Product;
import com.training.streams.model.SampleData;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Example 10 - Collectors.toMap.
 *
 * <p>Idea: {@code toMap(keyFunction, valueFunction)} builds a Map. Two traps:
 * a duplicate key throws {@code IllegalStateException} (fix: a merge function),
 * and a {@code null} value throws {@code NullPointerException}.
 */
public class Example10_CollectToMap {

    public static void main(String[] args) {
        List<Product> products = SampleData.products();

        // Unique keys: SKU -> price. TreeMap::new as 4th argument only to get sorted, readable output.
        Map<String, Double> priceBySku = products.stream()
                .limit(3)
                .collect(Collectors.toMap(Product::sku, Product::price, (a, b) -> a, TreeMap::new));
        System.out.println("sku -> price       : " + priceBySku);
        // prints {kb-100=120.0, mn-300=360.0, ms-200=40.0}

        // Duplicate keys: category is NOT unique, so the 2-argument toMap fails.
        try {
            products.stream().collect(Collectors.toMap(Product::category, Product::name));
        } catch (IllegalStateException e) {
            // prints: Duplicate key electronics (attempted merging values Mechanical Keyboard and Wireless Mouse)
            System.out.println("duplicate key      : " + e.getMessage());
        }

        // Fix: a merge function (a BinaryOperator) decides what to keep when a key repeats.
        // Here: keep the higher price per category.
        Map<String, Double> maxPriceByCategory = products.stream()
                .collect(Collectors.toMap(Product::category, Product::price, Math::max, TreeMap::new));
        System.out.println("category -> max    : " + maxPriceByCategory);
        // prints {electronics=360.0, furniture=480.0, stationery=4.0}

        // null values are not allowed in toMap (even though HashMap itself accepts null values).
        try {
            Stream.of("a").collect(Collectors.toMap(s -> s, s -> (String) null));
        } catch (NullPointerException e) {
            System.out.println("null value         : throws " + e.getClass().getSimpleName());
        }
    }
}
