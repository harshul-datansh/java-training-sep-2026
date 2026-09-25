package com.training.streams.model;

/**
 * One line of an {@link Order}: which product and how many.
 *
 * @param sku      the product code
 * @param quantity how many units were ordered
 */
public record OrderLine(String sku, int quantity) {
}
