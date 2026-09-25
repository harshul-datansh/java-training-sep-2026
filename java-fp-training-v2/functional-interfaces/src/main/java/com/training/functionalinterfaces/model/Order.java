package com.training.functionalinterfaces.model;

/**
 * A (very) simplified web-shop order. Used by Exercise 5.
 *
 * @param id              order id, e.g. "ORD-1001"
 * @param paid            true once the payment has been captured
 * @param total           order total in NOK
 * @param country         two-letter ISO country code of the delivery address, e.g. "NO", "SE"
 * @param flaggedForFraud true if the fraud checker has flagged this order
 */
public record Order(String id, boolean paid, double total, String country, boolean flaggedForFraud) {
}
