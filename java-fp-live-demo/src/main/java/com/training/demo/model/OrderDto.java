package com.training.demo.model;

/**
 * What our REST API sends to the front end: a slimmed-down view of an Order.
 * (DTO = Data Transfer Object.)
 *
 * @param id           order id
 * @param customer     customer email
 * @param totalWithVat total including 25% VAT, rounded to 2 decimals
 * @param itemCount    number of items in the order
 */
public record OrderDto(String id, String customer, double totalWithVat, int itemCount) {
}
