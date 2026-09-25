package com.training.streams.examples;

import com.training.streams.model.Product;
import com.training.streams.model.SampleData;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Example 07 - Terminal operations.
 *
 * <p>Idea: a terminal operation ends the pipeline and returns a RESULT (or performs an action).
 * Only then does the stream actually run.
 */
public class Example07_TerminalOperations {

    public static void main(String[] args) {
        List<Product> products = SampleData.products();

        // count: how many elements reach the end.
        long inStock = products.stream().filter(Product::inStock).count();
        System.out.println("count in stock     : " + inStock); // prints 5

        // anyMatch / allMatch / noneMatch: boolean questions. They stop as soon as the answer is known.
        System.out.println("anyMatch price>400 : " + products.stream().anyMatch(p -> p.price() > 400));  // prints true
        System.out.println("allMatch in stock  : " + products.stream().allMatch(Product::inStock));      // prints false
        System.out.println("noneMatch price<0  : " + products.stream().noneMatch(p -> p.price() < 0));   // prints true

        // findFirst: the first element, wrapped in Optional because the stream might be empty.
        Optional<String> firstFurniture = products.stream()
                .filter(p -> p.category().equals("furniture"))
                .map(Product::name)
                .findFirst();
        System.out.println("findFirst furniture: " + firstFurniture); // prints Optional[Standing Desk]

        // min / max with a Comparator. Also Optional.
        Optional<Product> cheapest = products.stream().min(Comparator.comparingDouble(Product::price));
        System.out.println("min by price       : " + cheapest.map(Product::name)); // prints Optional[Gel Pen]

        // reduce(identity, accumulator): combine all elements into one value.
        // identity 0.0 = the starting value AND the result for an empty stream.
        double total = products.stream()
                .map(Product::price)
                .reduce(0.0, (runningTotal, price) -> runningTotal + price);
        System.out.println("reduce total       : " + total); // prints 1282.0

        // The same with the specialised DoubleStream, which is what you would normally write.
        System.out.println("mapToDouble().sum(): " + products.stream().mapToDouble(Product::price).sum()); // prints 1282.0

        // forEach: performs an action for each element and returns nothing (void).
        // Fine for printing; do NOT use it to fill another list (use collect/toList instead).
        System.out.print("forEach            : ");
        products.stream().limit(3).map(Product::sku).forEach(sku -> System.out.print(sku + " "));
        System.out.println(); // prints kb-100 ms-200 mn-300

        // collect / toList: build a collection. See Examples 09 - 12.
    }
}
