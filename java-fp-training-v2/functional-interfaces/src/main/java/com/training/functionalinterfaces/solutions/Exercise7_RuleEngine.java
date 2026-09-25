package com.training.functionalinterfaces.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Solution to Exercise 7 - A tiny generic rule engine.
 */
public class Exercise7_RuleEngine {

    public static <T> Predicate<T> allOf(List<Predicate<T>> predicates) {
        // "Always true" is the neutral starting value for AND (true && x == x),
        // just like 0 is for + and 1 is for *. It also gives the right answer for
        // an empty list: no rules means nothing failed.
        // Because we chain with and(...), the result short-circuits at the first false.
        Predicate<T> result = t -> true;
        for (Predicate<T> p : predicates) {
            result = result.and(p);
        }
        return result;
    }

    public static <T> Predicate<T> anyOf(List<Predicate<T>> predicates) {
        // "Always false" is the neutral value for OR (false || x == x),
        // and an empty list correctly means "nothing matched".
        Predicate<T> result = t -> false;
        for (Predicate<T> p : predicates) {
            result = result.or(p);
        }
        return result;
    }

    public static <T> UnaryOperator<T> pipeline(List<UnaryOperator<T>> operators) {
        // Pitfall: UnaryOperator inherits andThen from Function, and that method
        // returns Function<T, V> - NOT UnaryOperator<T>. So
        //   UnaryOperator<T> acc = ...; acc = acc.andThen(op);   // does not compile
        // We accumulate in a Function<T, T> and adapt it back with a method reference
        // at the end (result::apply fits UnaryOperator's T apply(T)).
        // andThen keeps list order: the first operator runs first.
        Function<T, T> result = UnaryOperator.identity();
        for (UnaryOperator<T> op : operators) {
            result = result.andThen(op);
        }
        return result::apply;
    }

    public static <T> List<String> failedRules(T value, Map<String, Predicate<T>> rules) {
        // The map gives each Predicate a name, which is what a user-facing error
        // message needs. We iterate the map directly, so we inherit its order:
        // a LinkedHashMap gives insertion order, a HashMap would give no guaranteed order.
        List<String> failed = new ArrayList<>();
        rules.forEach((name, rule) -> {
            if (!rule.test(value)) {
                failed.add(name);
            }
        });
        return failed;
    }
}
