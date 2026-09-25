package com.training.streams.solutions;

import com.training.streams.model.Invoice;
import com.training.streams.model.Product;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/** Solution for Exercise 5 - terminal operations. */
public class Exercise5_TerminalOperations {

    public boolean anyOutOfStock(List<Product> products) {
        // anyMatch short-circuits: it stops at the first out-of-stock product.
        // Predicate.not(...) (from Topic 2) reads better than p -> !p.inStock().
        return products.stream().anyMatch(Predicate.not(Product::inStock));
    }

    public boolean allPricesPositive(List<Product> products) {
        // allMatch stops at the first product that FAILS the test.
        // For an empty stream allMatch is true ("no counter-example exists"), like "for all" in maths.
        return products.stream().allMatch(p -> p.price() > 0);
    }

    public long countByCategory(List<Product> products, String category) {
        // count() is a terminal operation returning long. No need to collect a list just to call size().
        return products.stream()
                .filter(p -> p.category().equals(category))
                .count();
    }

    public double totalPrice(List<Product> products) {
        // reduce(identity, accumulator): start with 0.0 and add each price to the running total.
        // 0.0 is the identity of addition (x + 0.0 == x), and it is also the result for an empty list.
        // Using e.g. 1.0 as identity would silently add 1 to every total.
        // (mapToDouble(Product::price).sum() is the everyday shortcut; reduce shows what sum does inside.)
        return products.stream()
                .map(Product::price)
                .reduce(0.0, Double::sum);
    }

    public Optional<String> firstOverdueInvoice(List<Invoice> invoices, LocalDate today) {
        // findFirst respects list order (findAny might not, especially in parallel streams).
        // isBefore is strict, so an invoice due today is not overdue.
        // Returning Optional forces the caller to handle "nothing overdue" instead of getting null.
        return invoices.stream()
                .filter(invoice -> !invoice.paid())
                .filter(invoice -> invoice.dueDate().isBefore(today))
                .findFirst()
                .map(Invoice::id);
    }

    public Optional<Double> highestPrice(List<Product> products) {
        // max needs a Comparator saying how to compare two products.
        // It returns Optional because an empty list has no maximum. We map to the price at the end.
        return products.stream()
                .max(Comparator.comparingDouble(Product::price))
                .map(Product::price);
    }
}
