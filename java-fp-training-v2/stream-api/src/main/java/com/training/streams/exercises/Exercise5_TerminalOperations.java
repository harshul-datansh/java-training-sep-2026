package com.training.streams.exercises;

import com.training.streams.model.Invoice;
import com.training.streams.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Exercise 5 - terminal operations.
 *
 * <p>A terminal operation ends the pipeline and produces a result: a boolean, a number,
 * an Optional or a collection. Nothing happens in a stream until one is called.
 *
 * <p>Examples with {@link com.training.streams.model.SampleData}:
 * <pre>
 *   anyOutOfStock(products)                     -> true   (e.g. the 4K Monitor)
 *   allPricesPositive(products)                 -> true
 *   countByCategory(products, "furniture")      -> 3
 *   totalPrice(products)                        -> 1282.0
 *   firstOverdueInvoice(invoices, 2026-02-15)   -> Optional[INV-2]
 *   highestPrice(products)                      -> Optional[480.0]
 * </pre>
 */
public class Exercise5_TerminalOperations {

    /**
     * @return true if at least one product is out of stock
     */
    public boolean anyOutOfStock(List<Product> products) {
        // TODO: answer "is there at least one ...?"
        // Hint: anyMatch stops at the first match. Predicate.not(...) or a lambda can express "not in stock".
        throw new UnsupportedOperationException("TODO: Exercise 5 - anyOutOfStock");
    }

    /**
     * @return true if every product has a price greater than 0 (also true for an empty list)
     */
    public boolean allPricesPositive(List<Product> products) {
        // TODO: answer "do ALL elements ...?"
        // Hint: allMatch. Ask yourself what "all elements of an empty list" should mean.
        throw new UnsupportedOperationException("TODO: Exercise 5 - allPricesPositive");
    }

    /**
     * @return how many products belong to the given category
     */
    public long countByCategory(List<Product> products, String category) {
        // TODO: filter, then count.
        // Hint: count() returns a long, not an int.
        throw new UnsupportedOperationException("TODO: Exercise 5 - countByCategory");
    }

    /**
     * The sum of all prices, computed with {@code reduce}.
     *
     * @return the total; 0.0 for an empty list
     */
    public double totalPrice(List<Product> products) {
        // TODO: turn products into prices, then combine them two at a time with reduce.
        // Hint: reduce(identity, accumulator). The identity is the value that changes nothing
        //       when combined, and it is also what you get back for an empty stream.
        //       Which number changes nothing when you ADD it?
        throw new UnsupportedOperationException("TODO: Exercise 5 - totalPrice");
    }

    /**
     * The id of the first invoice (in list order) that is unpaid AND whose due date is
     * strictly before {@code today}. An invoice due today is not overdue yet.
     *
     * @return for SampleData.invoices() and today = 2026-02-15: Optional[INV-2]
     */
    public Optional<String> firstOverdueInvoice(List<Invoice> invoices, LocalDate today) {
        // TODO: filter the overdue ones, take the first, return only its id.
        // Hint: LocalDate has isBefore(...). findFirst gives an Optional<Invoice>; Optional.map changes what is inside.
        throw new UnsupportedOperationException("TODO: Exercise 5 - firstOverdueInvoice");
    }

    /**
     * The highest price in the catalogue, found with {@code max(Comparator)}.
     *
     * @return the highest price, or {@code Optional.empty()} for an empty list
     */
    public Optional<Double> highestPrice(List<Product> products) {
        // TODO: find the most expensive product, then take its price.
        // Hint: max(Comparator.comparingDouble(...)) returns Optional<Product>.
        //       Why is the result an Optional and not a plain double?
        throw new UnsupportedOperationException("TODO: Exercise 5 - highestPrice");
    }
}
