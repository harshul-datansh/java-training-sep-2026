package com.training.demo.model;

import java.util.List;

/**
 * One customer order. This is the "running story" for the whole session:
 * every demo and exercise works with these orders.
 *
 * A record gives us a constructor, getters (id(), total(), ...), equals,
 * hashCode and toString for free.
 *
 * @param id            order id, e.g. "A-100"
 * @param customerEmail who placed the order
 * @param premium       true if the customer has a premium membership
 * @param total         order total in EUR
 * @param status        where the order is in its life cycle
 * @param skus          the item codes (SKUs) in the order
 */
public record Order(String id,
                    String customerEmail,
                    boolean premium,
                    double total,
                    OrderStatus status,
                    List<String> skus) {

    public Order {
        // Defensive copy: nobody can change our list from the outside.
        skus = List.copyOf(skus);
    }

    /** A tiny helper method; handy for method references (Order::isPaid). */
    public boolean isPaid() {
        return status == OrderStatus.PAID;
    }
}
