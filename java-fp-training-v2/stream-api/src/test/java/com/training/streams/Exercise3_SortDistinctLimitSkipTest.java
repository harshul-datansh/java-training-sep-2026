package com.training.streams;

import com.training.streams.exercises.Exercise3_SortDistinctLimitSkip;
import com.training.streams.model.Product;
import com.training.streams.model.SampleData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exercise 3 - sorted, distinct, limit, skip")
class Exercise3_SortDistinctLimitSkipTest {

    private final Exercise3_SortDistinctLimitSkip exercise = new Exercise3_SortDistinctLimitSkip();

    // After distinct + sorted: [apple, banana, cherry, fig, kiwi, pear, plum] (7 items)
    private final List<String> fruit =
            List.of("pear", "apple", "fig", "apple", "kiwi", "banana", "plum", "fig", "cherry");

    @Test
    @DisplayName("page 1 of size 3 is the first three sorted distinct items")
    void firstPage() {
        assertEquals(List.of("apple", "banana", "cherry"), exercise.page(fruit, 1, 3));
    }

    @Test
    @DisplayName("page 2 of size 3 skips the first three items")
    void secondPage() {
        assertEquals(List.of("fig", "kiwi", "pear"), exercise.page(fruit, 2, 3));
    }

    @Test
    @DisplayName("the last page can be shorter than the page size")
    void lastPartialPage() {
        assertEquals(List.of("plum"), exercise.page(fruit, 3, 3));
    }

    @Test
    @DisplayName("a page past the end is empty")
    void pagePastTheEnd() {
        assertEquals(List.of(), exercise.page(fruit, 4, 3));
    }

    @Test
    @DisplayName("page size larger than the list returns everything, sorted and distinct")
    void bigPage() {
        assertEquals(List.of("apple", "banana", "cherry", "fig", "kiwi", "pear", "plum"),
                exercise.page(fruit, 1, 50));
    }

    @Test
    @DisplayName("topNMostExpensive(3) returns the three highest prices, highest first")
    void topThree() {
        assertEquals(List.of("Standing Desk", "4K Monitor", "Office Chair"),
                exercise.topNMostExpensive(SampleData.products(), 3));
    }

    @Test
    @DisplayName("topNMostExpensive(1) returns only the most expensive product")
    void topOne() {
        assertEquals(List.of("Standing Desk"), exercise.topNMostExpensive(SampleData.products(), 1));
    }

    @Test
    @DisplayName("cheapestInStockName ignores the cheaper out-of-stock Gel Pen")
    void cheapestInStock() {
        assertEquals(Optional.of("Notebook"), exercise.cheapestInStockName(SampleData.products()));
    }

    @Test
    @DisplayName("cheapestInStockName is empty when nothing is in stock")
    void cheapestInStockNoneInStock() {
        List<Product> soldOut = List.of(
                new Product("a-1", "A", "x", 10.0, false),
                new Product("b-2", "B", "x", 5.0, false));
        assertEquals(Optional.empty(), exercise.cheapestInStockName(soldOut));
    }

    @Test
    @DisplayName("cheapestInStockName is empty for an empty catalogue")
    void cheapestInStockEmpty() {
        assertEquals(Optional.empty(), exercise.cheapestInStockName(List.of()));
    }
}
