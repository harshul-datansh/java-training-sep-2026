package com.training.streams.examples;

import com.training.streams.model.Product;
import com.training.streams.model.SampleData;

import java.util.Comparator;
import java.util.List;

/**
 * Example 06 - sorted, distinct, limit, skip.
 *
 * <p>Idea: these four intermediate operations reorder or cut the stream.
 * {@code sorted} and {@code distinct} are STATEFUL: they must see (many) elements before they
 * can pass anything on. {@code limit} and {@code skip} only count.
 */
public class Example06_SortedDistinctLimitSkip {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 3, 5, 1, 3, 8, 2);

        System.out.println("distinct        : " + numbers.stream().distinct().toList());
        // prints [5, 3, 1, 8, 2]   (first occurrence kept, order kept)
        System.out.println("sorted          : " + numbers.stream().sorted().toList());
        // prints [1, 2, 3, 3, 5, 5, 8]
        System.out.println("distinct+sorted : " + numbers.stream().distinct().sorted().toList());
        // prints [1, 2, 3, 5, 8]
        System.out.println("limit(3)        : " + numbers.stream().limit(3).toList());
        // prints [5, 3, 5]
        System.out.println("skip(5)         : " + numbers.stream().skip(5).toList());
        // prints [8, 2]

        // Sorting objects needs a Comparator. comparingDouble builds one from a getter.
        List<Product> products = SampleData.products();
        List<String> top3 = products.stream()
                .sorted(Comparator.comparingDouble(Product::price).reversed()) // most expensive first
                .limit(3)
                .map(Product::name)
                .toList();
        System.out.println("top 3 by price  : " + top3);
        // prints [Standing Desk, 4K Monitor, Office Chair]

        // Paging: page 2 with 3 items per page = skip 3, take 3.
        List<String> page2 = products.stream()
                .map(Product::name)
                .sorted()
                .skip(3)
                .limit(3)
                .toList();
        System.out.println("page 2 (size 3) : " + page2);
        // prints [Mechanical Keyboard, Notebook, Office Chair]
    }
}
