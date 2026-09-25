package com.training.functionalinterfaces.exercises;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Exercise 6 - Supplier laziness, memoization and Consumer chaining (medium/hard)
 *
 * <p>A {@link Supplier} is a piece of code that has NOT run yet. It runs every
 * time you call {@code get()} - not before, and not just once. Use that to:
 * <ul>
 *   <li>avoid computing an expensive fallback value unless it is really needed,</li>
 *   <li>build a Supplier that remembers (caches) its first result,</li>
 *   <li>build a stateful Supplier that gives a new value on every call.</li>
 * </ul>
 * Then chain two Consumers with {@code andThen}.
 *
 * <p>Example: {@code configValue(Map.of("db.url", "jdbc:x"), "db.url", () -> slowLookup())}
 * returns {@code "jdbc:x"} and NEVER calls {@code slowLookup()}.
 */
public class Exercise6_SupplierLaziness {

    /**
     * Returns the config value for {@code key}. If the key is missing, returns
     * {@code fallback.get()} instead. The fallback must ONLY be called when the key
     * is missing (it may be a slow call to a remote config server).
     *
     * <p>Examples: key present -> the map's value, fallback called 0 times;
     * key missing -> the fallback's value, fallback called exactly once.
     */
    public static String configValue(Map<String, String> config, String key, Supplier<String> fallback) {
        // TODO: look up the key; only call fallback.get() when there is no value
        // Hint: Map.getOrDefault(key, fallback.get()) looks tempting - but when is its second argument evaluated?
        throw new UnsupportedOperationException("TODO: Exercise 6 - configValue");
    }

    /**
     * Wraps {@code expensive} so that it is computed AT MOST ONCE: the first
     * {@code get()} calls {@code expensive.get()} and remembers the result;
     * later calls return the remembered value without calling it again.
     * Creating the memoized supplier must not call {@code expensive} at all (it stays lazy).
     *
     * <p>Example: calling {@code get()} three times on {@code memoize(() -> loadExchangeRates())}
     * calls {@code loadExchangeRates()} only once.
     * (You do not need to make it thread-safe for this exercise.)
     */
    public static <T> Supplier<T> memoize(Supplier<T> expensive) {
        // TODO: return a Supplier that caches the first result
        // Hint: a lambda cannot reassign a captured local variable (Topic 1: effectively final),
        //       but it CAN change what is inside a captured object - e.g. a one-element array or a small holder.
        //       Remember the result might legitimately be null, so track "computed yet?" separately.
        throw new UnsupportedOperationException("TODO: Exercise 6 - memoize");
    }

    /**
     * A Supplier of sequential ids: the first {@code get()} returns prefix + "-1",
     * the next prefix + "-2", and so on. Each call to {@code sequentialIds} starts its own sequence.
     *
     * <p>Example: {@code Supplier<String> ids = sequentialIds("ORD");} then
     * {@code ids.get()} gives "ORD-1", and {@code ids.get()} again gives "ORD-2".
     */
    public static Supplier<String> sequentialIds(String prefix) {
        // TODO: return a Supplier with its own counter
        // Hint: java.util.concurrent.atomic.AtomicInteger is a mutable counter object you can capture.
        throw new UnsupportedOperationException("TODO: Exercise 6 - sequentialIds");
    }

    /**
     * Returns a Consumer that adds each message to {@code primaryLog} and THEN to
     * {@code backupLog}.
     *
     * <p>Example: {@code logToBoth(a, b).accept("started")} leaves both a and b
     * containing {@code ["started"]}, with a written first.
     */
    public static Consumer<String> logToBoth(List<String> primaryLog, List<String> backupLog) {
        // TODO: build two Consumers and chain them
        // Hint: a method reference to List.add on a specific list is a Consumer. Consumer has a default method
        //       for "do this, then that".
        throw new UnsupportedOperationException("TODO: Exercise 6 - logToBoth");
    }
}
