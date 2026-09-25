package com.training.streams.model;

/**
 * A product in a web shop catalogue.
 *
 * <p>A {@code record} gives us a constructor, getters ({@code sku()}, {@code name()}, ...),
 * {@code equals}, {@code hashCode} and {@code toString} for free.
 *
 * @param sku      stock keeping unit, a short product code such as "kb-100"
 * @param name     human readable name
 * @param category category name, for example "electronics"
 * @param price    price excluding VAT
 * @param inStock  true if the product can be shipped today
 */
public record Product(String sku, String name, String category, double price, boolean inStock) {
}
