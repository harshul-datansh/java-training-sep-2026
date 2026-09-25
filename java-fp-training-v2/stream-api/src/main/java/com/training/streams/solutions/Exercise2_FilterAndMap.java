package com.training.streams.solutions;

import com.training.streams.model.Product;

import java.util.List;

/** Solution for Exercise 2 - filter and map. */
public class Exercise2_FilterAndMap {

    public List<String> namesInStockUnder(List<Product> products, double maxPrice) {
        // Filter BEFORE map: we still need the whole Product (inStock, price) to decide,
        // and after map(Product::name) we would only have a String left.
        // '<' not '<=' because the contract says "strictly less than".
        return products.stream()
                .filter(Product::inStock)
                .filter(p -> p.price() < maxPrice)
                .map(Product::name)
                .toList();
    }

    public List<String> upperCaseSkusOfCategory(List<Product> products, String category) {
        // category.equals(p.category()) compares text. '==' would compare object identity: a classic bug.
        // Two small maps read better than one lambda doing two jobs.
        return products.stream()
                .filter(p -> p.category().equals(category))
                .map(Product::sku)
                .map(String::toUpperCase)
                .toList();
    }

    public List<Double> pricesWithVat(List<Product> products, double vatRate) {
        // A pure one-to-one transformation: map only. The products themselves are not changed
        // (records are immutable anyway); we build a new list of numbers.
        double factor = 1 + vatRate;
        return products.stream()
                .map(Product::price)
                .map(price -> price * factor)
                .toList();
    }
}
