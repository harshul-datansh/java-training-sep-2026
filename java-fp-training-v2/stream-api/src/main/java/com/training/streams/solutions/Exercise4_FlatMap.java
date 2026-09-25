package com.training.streams.solutions;

import com.training.streams.model.Article;
import com.training.streams.model.Order;
import com.training.streams.model.OrderLine;

import java.util.List;

/** Solution for Exercise 4 - flatMap. */
public class Exercise4_FlatMap {

    public List<String> allTagsSorted(List<Article> articles) {
        // map(a -> a.tags()) would give Stream<List<String>>: a stream of 4 lists.
        // flatMap(a -> a.tags().stream()) opens every list and gives Stream<String>: all tags in one line.
        // An empty tag list simply contributes nothing, no special case needed.
        return articles.stream()
                .flatMap(article -> article.tags().stream())
                .distinct()
                .sorted()
                .toList();
    }

    public int totalQuantity(List<Order> orders) {
        // Order -> many OrderLines (flatMap), OrderLine -> int (mapToInt), then IntStream.sum().
        // mapToInt avoids boxing every number into an Integer, and sum() returns 0 for an empty stream.
        return orders.stream()
                .flatMap(order -> order.lines().stream())
                .mapToInt(OrderLine::quantity)
                .sum();
    }

    public List<String> skusForCustomer(List<Order> orders, String customer) {
        // Filter on the Order level first: after flatMap we only have OrderLines,
        // which do not know their customer any more.
        // distinct() keeps the FIRST occurrence and preserves order, which is exactly "first seen".
        return orders.stream()
                .filter(order -> order.customer().equals(customer))
                .flatMap(order -> order.lines().stream())
                .map(OrderLine::sku)
                .distinct()
                .toList();
    }
}
