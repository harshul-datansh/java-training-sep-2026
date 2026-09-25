package com.training.streams.exercises;

import com.training.streams.model.Product;

import java.util.List;

/**
 * Exercise 2 - filter and map.
 *
 * <p>{@code filter} keeps some elements, {@code map} turns each element into something else.
 * Together they replace the classic "loop + if + add to a new list" pattern.
 *
 * <p>Example with {@link com.training.streams.model.SampleData#products()}:
 * <pre>
 *   namesInStockUnder(products, 100.0)        -> ["Wireless Mouse", "Notebook", "Desk Lamp"]
 *   upperCaseSkusOfCategory(products, "furniture") -> ["DK-400", "CH-500", "LP-800"]
 *   pricesWithVat(products, 0.25)             -> [150.0, 50.0, 450.0, 600.0, 300.0, 5.0, 2.5, 45.0]
 * </pre>
 * Results keep the order of the input list.
 */
public class Exercise2_FilterAndMap {

    /**
     * Names of products that are in stock AND cost strictly less than {@code maxPrice}.
     *
     * @param products the catalogue
     * @param maxPrice exclusive upper limit (a product priced exactly maxPrice is NOT included)
     * @return product names in the original order
     */
    public List<String> namesInStockUnder(List<Product> products, double maxPrice) {
        // TODO: keep the right products, then turn each one into its name.
        // Hint: two conditions can live in one filter, or in two filters one after the other.
        //       A method reference such as Product::name is a neat Function<Product, String>.
        throw new UnsupportedOperationException("TODO: Exercise 2 - namesInStockUnder");
    }

    /**
     * SKUs of all products in the given category, converted to upper case.
     *
     * @param products the catalogue
     * @param category exact category name, for example "furniture"
     * @return for "furniture": ["DK-400", "CH-500", "LP-800"]
     */
    public List<String> upperCaseSkusOfCategory(List<Product> products, String category) {
        // TODO: filter by category, then map twice (or once).
        // Hint: map can be chained: first Product -> String (the sku), then String -> String (upper case).
        throw new UnsupportedOperationException("TODO: Exercise 2 - upperCaseSkusOfCategory");
    }

    /**
     * The price of every product with VAT added.
     *
     * @param products the catalogue
     * @param vatRate  VAT as a fraction, for example 0.25 for 25 %
     * @return price * (1 + vatRate) for each product, in the original order
     */
    public List<Double> pricesWithVat(List<Product> products, double vatRate) {
        // TODO: one map to get the price, one to apply the rate.
        // Hint: there is no filter here. Every input element produces exactly one output element.
        throw new UnsupportedOperationException("TODO: Exercise 2 - pricesWithVat");
    }
}
