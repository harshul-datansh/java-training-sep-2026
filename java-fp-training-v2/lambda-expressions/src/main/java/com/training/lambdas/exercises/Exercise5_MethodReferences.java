package com.training.lambdas.exercises;

import com.training.lambdas.model.Customer;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Exercise 5 (medium): all four kinds of method reference.
 *
 * <p>Every method here should be ONE line: {@code return Something::something;}
 * No arrows allowed. The tests can only check behaviour, so be honest with
 * yourself: if you wrote a lambda, try to turn it into a method reference.
 *
 * <p>The four kinds (Examples 06 to 09):
 * <ol>
 *   <li>static method:                   {@code ClassName::staticMethod}</li>
 *   <li>method on a particular object:   {@code someObject::method}</li>
 *   <li>method on an arbitrary object:   {@code ClassName::instanceMethod}</li>
 *   <li>constructor:                     {@code ClassName::new}</li>
 * </ol>
 */
public class Exercise5_MethodReferences {

    /** Kind 1. Parses {@code "42"} into {@code 42}. Same as {@code s -> Integer.parseInt(s)}. */
    public static Function<String, Integer> parseQuantity() {
        // TODO: kind 1 (static).
        // Hint: the lambda only forwards its parameter to a static method.
        throw new UnsupportedOperationException("TODO: Exercise 5 - parseQuantity");
    }

    /** Kind 2. True if the code is in {@code knownCurrencies}. Same as {@code c -> knownCurrencies.contains(c)}. */
    public static Predicate<String> isKnownCurrency(Set<String> knownCurrencies) {
        // TODO: kind 2 (particular object).
        // Hint: which specific object is the method called on? Put it left of ::
        throw new UnsupportedOperationException("TODO: Exercise 5 - isKnownCurrency");
    }

    /** Kind 3. Removes leading and trailing whitespace. Same as {@code s -> s.trim()}. */
    public static Function<String, String> trim() {
        // TODO: kind 3 (arbitrary object).
        // Hint: the parameter itself is the object the method is called on.
        throw new UnsupportedOperationException("TODO: Exercise 5 - trim");
    }

    /**
     * Kind 3 with TWO parameters. True if both strings are equal ignoring case.
     * Same as {@code (a, b) -> a.equalsIgnoreCase(b)}.
     */
    public static BiFunction<String, String, Boolean> sameIgnoringCase() {
        // TODO: kind 3 (arbitrary object), two parameters.
        // Hint: the FIRST parameter becomes the object, the second the argument.
        throw new UnsupportedOperationException("TODO: Exercise 5 - sameIgnoringCase");
    }

    /** Kind 4. Creates a Customer from a name. Same as {@code name -> new Customer(name)}. */
    public static Function<String, Customer> customerFromName() {
        // TODO: kind 4 (constructor).
        // Hint: Customer has a one-argument constructor; Function.apply has one argument.
        throw new UnsupportedOperationException("TODO: Exercise 5 - customerFromName");
    }

    /** Kind 4. Every call to get() returns a NEW, empty, modifiable list. */
    public static Supplier<List<String>> newAuditLog() {
        // TODO: kind 4 (constructor).
        // Hint: List is an interface and can't be constructed. Pick a class that implements it.
        throw new UnsupportedOperationException("TODO: Exercise 5 - newAuditLog");
    }
}
