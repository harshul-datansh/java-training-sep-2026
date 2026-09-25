package com.training.streams.examples;

import com.training.streams.model.Product;
import com.training.streams.model.SampleData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Example 12 - Collectors.joining.
 *
 * <p>Idea: {@code joining} glues a stream of Strings into one String, with an optional
 * delimiter, prefix and suffix. No more "remove the last comma" code.
 */
public class Example12_Joining {

    public static void main(String[] args) {
        List<Product> products = SampleData.products();

        String skus = products.stream()
                .filter(p -> p.category().equals("furniture"))
                .map(Product::sku)
                .collect(Collectors.joining());
        System.out.println("joining()            : " + skus);
        // prints dk-400ch-500lp-800

        String csv = products.stream()
                .filter(p -> p.category().equals("furniture"))
                .map(Product::sku)
                .collect(Collectors.joining(","));
        System.out.println("joining(\",\")         : " + csv);
        // prints dk-400,ch-500,lp-800

        String sentence = products.stream()
                .filter(p -> p.category().equals("furniture"))
                .map(Product::name)
                .collect(Collectors.joining(", ", "Furniture: ", "."));
        System.out.println("joining(d, pre, suf) : " + sentence);
        // prints Furniture: Standing Desk, Office Chair, Desk Lamp.

        // Prefix and suffix are added even when the stream is empty.
        String none = products.stream()
                .filter(p -> p.category().equals("garden"))
                .map(Product::name)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("empty stream         : " + none);
        // prints []
    }
}
