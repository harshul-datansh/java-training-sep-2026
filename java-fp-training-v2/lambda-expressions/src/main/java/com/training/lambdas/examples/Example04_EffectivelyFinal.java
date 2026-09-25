package com.training.lambdas.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Example 04: capturing local variables, and WHY they must be effectively final.
 *
 * <p>"Effectively final" means: you never assign the variable again after its
 * first assignment. You don't have to write the {@code final} keyword; the
 * compiler checks it for you.
 */
public class Example04_EffectivelyFinal {

    public static void main(String[] args) {
        // ------------------------------------------------------------
        // 1. Capturing works: the lambda uses a local variable from outside.
        // ------------------------------------------------------------
        double vatRate = 0.25;                         // assigned once -> effectively final
        Function<Double, Double> withVat = price -> price * (1 + vatRate);
        System.out.println("100 with VAT: " + withVat.apply(100.0)); // prints 125.0

        // ------------------------------------------------------------
        // 2. WHY the rule exists: a lambda can run LATER than it was created.
        // ------------------------------------------------------------
        // makeGreeter() creates a lambda and returns it. By the time we call
        // greeter.get(), makeGreeter() has already FINISHED, so its local
        // variable "name" no longer exists on the stack.
        Supplier<String> greeter = makeGreeter("Ada");
        System.out.println(greeter.get()); // prints Hello, Ada

        // How can the lambda still see "name"? Java COPIED the value into the
        // lambda object when the lambda was created. The lambda carries its
        // own private copy around.
        //
        // Now imagine Java allowed this:
        //
        //     String name = "Ada";
        //     Supplier<String> s = () -> "Hello, " + name;   // copy made: "Ada"
        //     name = "Bob";                                  // change the original
        //     s.get();                                       // "Ada" or "Bob"??
        //
        // The copy says "Ada", the variable says "Bob". Code that LOOKS like it
        // reads one variable would silently read a stale copy. The same
        // confusion gets far worse when the lambda runs on another thread
        // (thread pools, Kafka listeners, CompletableFuture...).
        //
        // Java's answer: if the variable never changes, the copy and the
        // original are always identical, so the question can never come up.
        // That is the whole reason for "effectively final".

        // ------------------------------------------------------------
        // 3. What the compiler says when you break the rule
        // ------------------------------------------------------------
        // int count = 0;
        // Runnable r = () -> count++;
        //   error: local variables referenced from a lambda expression
        //          must be final or effectively final
        //
        // double rate = 0.25;
        // Function<Double, Double> f = p -> p * rate;
        // rate = 0.15;      // this line makes 'rate' NOT effectively final,
        //                   // so the lambda above stops compiling

        // ------------------------------------------------------------
        // 4. The fix is usually to NOT mutate, not to trick the compiler.
        // ------------------------------------------------------------
        // Beginners often "fix" the error with a one-element array:
        //     int[] count = {0};
        //     list.forEach(x -> count[0]++);
        // It compiles (the array variable never changes, only its contents),
        // but it hides the problem and is NOT thread-safe. Prefer code that
        // returns a value instead of mutating an outside variable:
        List<String> orders = new ArrayList<>(List.of("A-1", "B-2", "A-3"));
        long aOrders = orders.stream().filter(o -> o.startsWith("A")).count();
        System.out.println("orders starting with A: " + aOrders); // prints 2
        // (Streams are Topic 3. The point here: count() returns the answer,
        //  so no captured variable needs to change.)

        // ------------------------------------------------------------
        // 5. Fields are different
        // ------------------------------------------------------------
        // A lambda inside an instance method may read AND change fields,
        // because it captures 'this' (a reference that never changes), not a
        // copy of the field. The rule only applies to LOCAL variables and
        // method parameters.
        Counter counter = new Counter();
        Runnable increment = counter.incrementer();
        increment.run();
        increment.run();
        System.out.println("counter field: " + counter.value); // prints 2
    }

    private static Supplier<String> makeGreeter(String name) {
        // 'name' is a parameter that is never reassigned -> effectively final.
        return () -> "Hello, " + name;
    }

    static class Counter {
        int value;

        Runnable incrementer() {
            return () -> value++; // really this.value++ : allowed, it's a field
        }
    }
}
