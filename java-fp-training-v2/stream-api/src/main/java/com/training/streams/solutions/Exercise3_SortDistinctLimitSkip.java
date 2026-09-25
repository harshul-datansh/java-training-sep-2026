package com.training.streams.solutions;

import com.training.streams.model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/** Solution for Exercise 3 - sorted, distinct, limit and skip. */
public class Exercise3_SortDistinctLimitSkip {

    public List<String> page(List<String> items, int pageNumber, int pageSize) {
        // distinct and sorted must come BEFORE skip/limit: paging is defined on the cleaned list.
        // Pages are 1-based, so page 1 skips 0 items, page 2 skips pageSize items, and so on.
        // The cast to long avoids int overflow for huge page numbers (skip takes a long).
        // Past the end, skip simply drops everything and we get an empty list, no exception.
        return items.stream()
                .distinct()
                .sorted()
                .skip((long) (pageNumber - 1) * pageSize)
                .limit(pageSize)
                .toList();
    }

    public List<String> topNMostExpensive(List<Product> products, int n) {
        // Sort on the Product (we need the price), and only map to the name at the very end.
        // If we mapped to names first we would lose the price and could only sort alphabetically.
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::price).reversed())
                .limit(n)
                .map(Product::name)
                .toList();
    }

    public Optional<String> cheapestInStockName(List<Product> products) {
        // Filter first, so an out-of-stock product can never "win".
        // findFirst returns Optional because the stream may be empty. Optional.map converts
        // the product to its name only if there is one: no null checks, no get().
        // (min(comparator) would do the same in one step; sorted + findFirst is used here to practise.)
        return products.stream()
                .filter(Product::inStock)
                .sorted(Comparator.comparingDouble(Product::price))
                .findFirst()
                .map(Product::name);
    }
}
