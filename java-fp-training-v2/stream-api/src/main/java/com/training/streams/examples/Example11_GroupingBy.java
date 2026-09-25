package com.training.streams.examples;

import com.training.streams.model.Product;
import com.training.streams.model.SampleData;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Example 11 - Collectors.groupingBy.
 *
 * <p>Idea: {@code groupingBy(classifier)} puts elements into buckets by key, giving
 * {@code Map<Key, List<Element>>}. An optional second argument (a "downstream" collector)
 * says what each bucket should become instead of a list: a count, a sum, a list of names...
 *
 * <p>{@code TreeMap::new} is passed only so the printed keys are sorted and the output is stable.
 */
public class Example11_GroupingBy {

    public static void main(String[] args) {
        List<Product> products = SampleData.products();

        // Category -> list of names. mapping() converts each element inside its group.
        Map<String, List<String>> namesByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        TreeMap::new,
                        Collectors.mapping(Product::name, Collectors.toList())));
        System.out.println("names by category : " + namesByCategory);
        // prints {electronics=[Mechanical Keyboard, Wireless Mouse, 4K Monitor],
        //         furniture=[Standing Desk, Office Chair, Desk Lamp], stationery=[Notebook, Gel Pen]}

        // Category -> how many products. counting() gives a Long.
        Map<String, Long> countByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category, TreeMap::new, Collectors.counting()));
        System.out.println("count by category : " + countByCategory);
        // prints {electronics=3, furniture=3, stationery=2}

        // Key can be anything computed from the element, e.g. a boolean.
        Map<Boolean, Long> countByStock = products.stream()
                .collect(Collectors.groupingBy(Product::inStock, TreeMap::new, Collectors.counting()));
        System.out.println("count by inStock  : " + countByStock);
        // prints {false=3, true=5}
    }
}
