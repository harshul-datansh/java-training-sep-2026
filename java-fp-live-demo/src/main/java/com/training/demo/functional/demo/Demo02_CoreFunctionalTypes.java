package com.training.demo.functional.demo;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * DEMO 2 - The core types in java.util.function.
 *
 * You almost never need to write your own interface: pick by the SHAPE
 * "what goes in -> what comes out".
 */
public class Demo02_CoreFunctionalTypes {

    public static void main(String[] args) {
        Order order = SampleData.orders().get(0); // A-100, anna, premium, 120.00, PAID

        // Function<T, R>: one thing in, a DIFFERENT thing out.  T -> R      method: apply
        Function<Order, String> toEmail = o -> o.customerEmail();

        // BiFunction<T, U, R>: two things in, one out.         (T, U) -> R  method: apply
        BiFunction<Order, Double, Double> totalWithTax = (o, rate) -> o.total() * (1 + rate);

        // Supplier<T>: nothing in, something out.               () -> T      method: get
        Supplier<String> newOrderId = () -> "A-" + UUID.randomUUID().toString().substring(0, 4);

        // Consumer<T>: something in, nothing out (a side effect).  T -> void  method: accept
        Consumer<Order> audit = o -> System.out.println("[audit] saw order " + o.id());

        // BiConsumer<T, U>: two things in, nothing out.         (T, U) -> void  method: accept
        BiConsumer<Order, String> notify = (o, msg) -> System.out.println("[mail to " + o.customerEmail() + "] " + msg);

        // Predicate<T>: something in, true/false out.            T -> boolean  method: test
        Predicate<Order> isPaid = o -> o.status() == OrderStatus.PAID;

        // BiPredicate<T, U>: two in, true/false out.            (T, U) -> boolean  method: test
        BiPredicate<Order, String> containsSku = (o, sku) -> o.skus().contains(sku);

        // UnaryOperator<T>: a Function whose input and output are the SAME type.  T -> T
        UnaryOperator<String> normalize = s -> s.trim().toLowerCase();

        // BinaryOperator<T>: a BiFunction where all three types are the same.  (T, T) -> T
        BinaryOperator<Double> sum = (a, b) -> a + b;

        System.out.println("=== One call of each core type ===");
        System.out.println("Function       apply  -> " + toEmail.apply(order));
        System.out.println("BiFunction     apply  -> " + totalWithTax.apply(order, 0.25));
        System.out.println("Supplier       get    -> " + newOrderId.get() + "   (random, differs each run)");
        System.out.print("Consumer       accept -> ");
        audit.accept(order);
        System.out.print("BiConsumer     accept -> ");
        notify.accept(order, "your order has shipped");
        System.out.println("Predicate      test   -> " + isPaid.test(order));
        System.out.println("BiPredicate    test   -> contains BOOK-1? " + containsSku.test(order, "BOOK-1"));
        System.out.println("UnaryOperator  apply  -> '" + normalize.apply("  ANNA@Shop.test ") + "'");
        System.out.println("BinaryOperator apply  -> " + sum.apply(120.0, 35.5));
        System.out.println();

        // They shine when you pass them to methods that expect them:
        List<Order> orders = SampleData.orders();
        System.out.println("=== Passing them to the JDK ===");
        System.out.println("removeIf(Predicate) on a copy, paid removed -> "
                + removePaid(orders).stream().map(Order::id).toList());
        System.out.println("replaceAll(UnaryOperator) on emails       -> " + upperEmails(orders));
    }

    private static List<Order> removePaid(List<Order> orders) {
        List<Order> copy = new java.util.ArrayList<>(orders);
        copy.removeIf(o -> o.status() == OrderStatus.PAID);   // removeIf takes a Predicate
        return copy;
    }

    private static List<String> upperEmails(List<Order> orders) {
        List<String> emails = new java.util.ArrayList<>();
        orders.forEach(o -> emails.add(o.customerEmail()));    // forEach takes a Consumer
        emails.replaceAll(String::toUpperCase);                // replaceAll takes a UnaryOperator
        return emails.subList(0, 3);
    }
}
