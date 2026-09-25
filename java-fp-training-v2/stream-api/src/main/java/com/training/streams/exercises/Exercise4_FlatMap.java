package com.training.streams.exercises;

import com.training.streams.model.Article;
import com.training.streams.model.Order;

import java.util.List;

/**
 * Exercise 4 - flatMap.
 *
 * <p>Use {@code flatMap} when each element has a LIST of things and you want one flat
 * stream of all those things. {@code map} would give you a stream of lists instead.
 *
 * <p>Examples with {@link com.training.streams.model.SampleData}:
 * <pre>
 *   articles: "Streams 101" [java, streams, beginner], "Records in practice" [java, records],
 *             "Lambdas deep dive" [lambdas, java, advanced], "No tags yet" []
 *   allTagsSorted(articles) -> [advanced, beginner, java, lambdas, records, streams]
 *
 *   orders: o1 alice [KB x2, MS x1], o2 bob [MS x3], o3 alice [MN x1, KB x1], o4 carol []
 *   totalQuantity(orders)              -> 8
 *   skusForCustomer(orders, "alice")   -> [KB, MS, MN]
 * </pre>
 */
public class Exercise4_FlatMap {

    /**
     * All tags used by any article, without duplicates, sorted alphabetically.
     *
     * @param articles the articles (an article may have no tags)
     * @return sorted distinct tags
     */
    public List<String> allTagsSorted(List<Article> articles) {
        // TODO: flatten the tags of all articles into one stream, then clean it up.
        // Hint: flatMap needs a function that returns a STREAM. A List becomes a stream with .stream().
        throw new UnsupportedOperationException("TODO: Exercise 4 - allTagsSorted");
    }

    /**
     * The sum of the quantities of all lines of all orders.
     *
     * @param orders the orders (an order may have no lines)
     * @return total number of units ordered
     */
    public int totalQuantity(List<Order> orders) {
        // TODO: flatten orders into order lines, then add up the quantities.
        // Hint: mapToInt(...) gives an IntStream, and IntStream has a ready-made sum().
        throw new UnsupportedOperationException("TODO: Exercise 4 - totalQuantity");
    }

    /**
     * The SKUs ordered by one customer across all of that customer's orders,
     * without duplicates, in the order they were first seen.
     *
     * @param orders   the orders
     * @param customer the customer username, for example "alice"
     * @return for "alice": [KB, MS, MN]; an empty list for an unknown customer
     */
    public List<String> skusForCustomer(List<Order> orders, String customer) {
        // TODO: pick the customer's orders, flatten to lines, turn lines into SKUs, remove duplicates.
        // Hint: filter works on orders, so do it BEFORE flattening. distinct keeps the first occurrence.
        throw new UnsupportedOperationException("TODO: Exercise 4 - skusForCustomer");
    }
}
