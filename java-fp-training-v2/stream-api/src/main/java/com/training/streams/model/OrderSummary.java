package com.training.streams.model;

/**
 * A small response DTO: what an API would return to the front end for one order.
 * This is an OUTPUT of the order report exercise.
 *
 * @param orderId      the order id
 * @param customerName the customer display name
 * @param totalAmount  the order total
 */
public record OrderSummary(long orderId, String customerName, double totalAmount) {

    /** Convenience factory: build a summary from a full order. Handy as a method reference. */
    public static OrderSummary from(OrderDto order) {
        return new OrderSummary(order.id(), order.customerName(), order.totalAmount());
    }
}
