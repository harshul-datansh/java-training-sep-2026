package com.training.lambdas.model;

/**
 * A tiny customer type shared by examples, exercises, solutions and tests.
 *
 * <p>It is a {@code record}: Java generates the constructor, the accessors
 * {@code name()} and {@code email()}, plus equals/hashCode/toString for us.
 */
public record Customer(String name, String email) {

    /**
     * Extra one-argument constructor, used to show that a constructor
     * reference ({@code Customer::new}) picks the constructor whose
     * parameters match the functional interface.
     */
    public Customer(String name) {
        this(name, name.toLowerCase() + "@example.com");
    }
}
