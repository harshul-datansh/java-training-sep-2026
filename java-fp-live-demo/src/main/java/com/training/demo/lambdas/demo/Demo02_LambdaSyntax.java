package com.training.demo.lambdas.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * DEMO 2 - Anatomy of a lambda.
 *
 *      (Order o)   ->   o.total() > 100
 *      ---------   --   ----------------
 *      parameters arrow      body
 *
 * A lambda is "the body of the one method", written as a value you can store and pass around.
 */
public class Demo02_LambdaSyntax {

    public static void main(String[] args) {
        Order order = SampleData.orders().get(0); // A-100, total 120.00

        // 1) Full form: typed parameter, braces, explicit return.
        Predicate<Order> full = (Order o) -> {
            return o.total() > 100;
        };

        // 2) Drop the type: the compiler infers "o is an Order" from Predicate<Order>.
        Predicate<Order> noType = (o) -> {
            return o.total() > 100;
        };

        // 3) ONE parameter -> the parentheses are optional.
        // 4) ONE expression as the body -> drop the braces AND the word return.
        Predicate<Order> shortest = o -> o.total() > 100;

        System.out.println("=== Same predicate, three spellings (A-100, total 120.00) ===");
        System.out.println("full     : " + full.test(order));
        System.out.println("noType   : " + noType.test(order));
        System.out.println("shortest : " + shortest.test(order));
        System.out.println();

        // Zero parameters -> you MUST write empty parentheses ().
        Supplier<String> greeting = () -> "Welcome to the order service";

        // Two or more parameters -> parentheses are required.
        BiFunction<Double, Double, Double> addShipping = (total, shipping) -> total + shipping;

        // More than one statement -> braces + return are required.
        Function<Order, String> describe = o -> {
            String kind = o.premium() ? "premium" : "standard";
            return o.id() + " is a " + kind + " order";
        };

        // Lambdas also fit interfaces from the JDK you already know:
        Comparator<Order> byId = (a, b) -> a.id().compareTo(b.id());

        System.out.println("=== Other shapes ===");
        System.out.println("zero params  : " + greeting.get());
        System.out.println("two params   : " + addShipping.apply(120.0, 4.99));
        System.out.println("block body   : " + describe.apply(order));
        System.out.println("Comparator   : compare(A-100, A-101) = "
                + byId.compare(order, SampleData.orders().get(1)));
    }
}
