package com.training.streams.model;

/**
 * A response DTO with statistics for one customer.
 * This is an OUTPUT of the order report exercise.
 *
 * @param customerEmail the customer e-mail (used as the key)
 * @param orderCount    how many orders were counted
 * @param totalAmount   sum of the order totals
 * @param averageAmount totalAmount / orderCount
 */
public record CustomerReport(String customerEmail, long orderCount, double totalAmount, double averageAmount) {
}
