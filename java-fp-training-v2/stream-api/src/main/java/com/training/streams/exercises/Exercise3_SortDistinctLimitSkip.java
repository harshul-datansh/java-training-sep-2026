package com.training.streams.exercises;

import com.training.streams.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 3 - sorted, distinct, limit and skip.
 *
 * <p>These four operations are how you build "page 2 of the search results" or
 * "top 3 best sellers" without writing index arithmetic in a loop.
 *
 * <p>Examples:
 * <pre>
 *   items = ["pear", "apple", "fig", "apple", "kiwi", "banana", "plum", "fig", "cherry"]
 *   sorted + distinct = [apple, banana, cherry, fig, kiwi, pear, plum]
 *
 *   page(items, 1, 3) -> [apple, banana, cherry]
 *   page(items, 2, 3) -> [fig, kiwi, pear]
 *   page(items, 3, 3) -> [plum]
 *   page(items, 4, 3) -> []
 *
 *   topNMostExpensive(SampleData.products(), 2)  -> ["Standing Desk", "4K Monitor"]
 *   cheapestInStockName(SampleData.products())   -> Optional[Notebook]
 *       (the Gel Pen is cheaper, but it is out of stock)
 * </pre>
 */
public class Exercise3_SortDistinctLimitSkip {

    /**
     * Returns one page of the items after removing duplicates and sorting them alphabetically.
     *
     * @param items      raw items, may contain duplicates, in any order
     * @param pageNumber 1-based page number (1 is the first page)
     * @param pageSize   how many items per page
     * @return the items on that page; an empty list if the page is past the end
     */
    public List<String> page(List<String> items, int pageNumber, int pageSize) {
        // TODO: clean up the items, then jump to the right page.
        // Hint: skip(n) throws away the first n elements, limit(n) keeps at most n.
        //       How many items come BEFORE page number p? (Careful: pages start at 1, not 0.)
        throw new UnsupportedOperationException("TODO: Exercise 3 - page");
    }

    /**
     * Names of the {@code n} most expensive products, most expensive first.
     *
     * @param products the catalogue (all prices are different)
     * @param n        how many names to return
     * @return for n = 3: ["Standing Desk", "4K Monitor", "Office Chair"]
     */
    public List<String> topNMostExpensive(List<Product> products, int n) {
        // TODO: sort by price from high to low, keep n, turn into names.
        // Hint: Comparator.comparingDouble(...) sorts low to high; look at .reversed().
        //       Think about whether map should come before or after sorted.
        throw new UnsupportedOperationException("TODO: Exercise 3 - topNMostExpensive");
    }

    /**
     * Name of the cheapest product that is in stock.
     *
     * @param products the catalogue
     * @return the name, or {@code Optional.empty()} if no product is in stock
     */
    public Optional<String> cheapestInStockName(List<Product> products) {
        // TODO: use sorted + findFirst for this one (not min).
        // Hint: findFirst returns an Optional<Product>. Optional has its own map method,
        //       so you can turn Optional<Product> into Optional<String> without get().
        throw new UnsupportedOperationException("TODO: Exercise 3 - cheapestInStockName");
    }
}
