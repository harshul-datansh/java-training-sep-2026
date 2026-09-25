package com.training.functionalinterfaces.solutions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Solution to Exercise 6 - Supplier laziness, memoization and Consumer chaining.
 */
public class Exercise6_SupplierLaziness {

    public static String configValue(Map<String, String> config, String key, Supplier<String> fallback) {
        // Pitfall: config.getOrDefault(key, fallback.get()) calls fallback.get()
        // BEFORE getOrDefault runs - Java evaluates arguments first - so the slow
        // call would happen every time. The whole point of taking a Supplier is
        // that WE decide when (and whether) to call get().
        String value = config.get(key);
        if (value != null) {
            return value;
        }
        return fallback.get();
    }

    public static <T> Supplier<T> memoize(Supplier<T> expensive) {
        // A Supplier does NOT cache - every get() re-runs its code. To cache we need
        // mutable state that lives as long as the returned Supplier.
        // We cannot reassign a captured local (effectively-final rule), so we
        // capture small arrays and change their CONTENTS instead.
        // A separate 'computed' flag is needed because null is a valid result.
        // (Not thread-safe; production code would use synchronisation.)
        boolean[] computed = {false};
        Object[] cached = {null};
        return () -> {
            if (!computed[0]) {
                cached[0] = expensive.get();
                computed[0] = true;
            }
            @SuppressWarnings("unchecked")
            T result = (T) cached[0];
            return result;
        };
    }

    public static Supplier<String> sequentialIds(String prefix) {
        // The counter is created once per sequentialIds(...) call and captured by
        // the lambda, so each returned Supplier has its own sequence. Each get()
        // runs the lambda again - which is exactly why the value changes.
        AtomicInteger counter = new AtomicInteger();
        return () -> prefix + "-" + counter.incrementAndGet();
    }

    public static Consumer<String> logToBoth(List<String> primaryLog, List<String> backupLog) {
        // primaryLog::add is a bound method reference (Topic 1): String -> boolean.
        // Consumer ignores the boolean. andThen gives the SAME message to both, primary first.
        Consumer<String> primary = primaryLog::add;
        Consumer<String> backup = backupLog::add;
        return primary.andThen(backup);
    }
}
