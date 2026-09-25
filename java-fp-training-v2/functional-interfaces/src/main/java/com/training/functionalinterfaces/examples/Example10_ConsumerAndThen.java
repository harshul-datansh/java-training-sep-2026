package com.training.functionalinterfaces.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Example 10 - Consumer.andThen: "do this, then also do that, with the same value".
 *
 * <p>{@code first.andThen(second)} returns a new Consumer. When you call
 * {@code accept(x)} on it, it calls {@code first.accept(x)} and then
 * {@code second.accept(x)} - both get the SAME input.
 *
 * <p>Compare with Function.andThen: there, the second function receives the
 * RESULT of the first. A Consumer has no result, so the second just gets the
 * original value again.
 */
public class Example10_ConsumerAndThen {

    public static void main(String[] args) {
        List<String> auditLog = new ArrayList<>();
        List<String> notifications = new ArrayList<>();

        Consumer<String> audit = event -> {
            System.out.println("  1. audit:  " + event);
            auditLog.add(event);
        };
        Consumer<String> notify = event -> {
            System.out.println("  2. notify: " + event);
            notifications.add("Email sent about " + event);
        };

        Consumer<String> auditThenNotify = audit.andThen(notify);

        System.out.println("accept(\"ORD-7 shipped\"):");
        auditThenNotify.accept("ORD-7 shipped");
        // prints   1. audit:  ORD-7 shipped
        // prints   2. notify: ORD-7 shipped

        System.out.println("auditLog      = " + auditLog);
        // prints auditLog      = [ORD-7 shipped]
        System.out.println("notifications = " + notifications);
        // prints notifications = [Email sent about ORD-7 shipped]

        // You can keep chaining. They always run left to right.
        Consumer<String> all = audit.andThen(notify).andThen(e -> System.out.println("  3. done:   " + e));
        System.out.println("accept(\"ORD-8 paid\"):");
        all.accept("ORD-8 paid");
        // prints   1. audit:  ORD-8 paid
        // prints   2. notify: ORD-8 paid
        // prints   3. done:   ORD-8 paid
    }
}
