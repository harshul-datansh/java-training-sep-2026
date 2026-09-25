package com.training.functionalinterfaces;

import com.training.functionalinterfaces.exercises.Exercise6_SupplierLaziness;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Exercise 6 - Supplier laziness")
class Exercise6_SupplierLazinessTest {

    private static final Map<String, String> CONFIG = Map.of("db.url", "jdbc:postgresql://db/shop");

    @Test
    @DisplayName("configValue returns the stored value and never calls the fallback")
    void configValuePresentDoesNotCallFallback() {
        AtomicInteger fallbackCalls = new AtomicInteger();
        Supplier<String> fallback = () -> {
            fallbackCalls.incrementAndGet();
            return "default";
        };

        String value = Exercise6_SupplierLaziness.configValue(CONFIG, "db.url", fallback);

        assertEquals("jdbc:postgresql://db/shop", value);
        assertEquals(0, fallbackCalls.get(), "fallback must not be called when the key exists");
    }

    @Test
    @DisplayName("configValue calls the fallback exactly once when the key is missing")
    void configValueMissingCallsFallbackOnce() {
        AtomicInteger fallbackCalls = new AtomicInteger();
        Supplier<String> fallback = () -> {
            fallbackCalls.incrementAndGet();
            return "default";
        };

        String value = Exercise6_SupplierLaziness.configValue(CONFIG, "cache.ttl", fallback);

        assertEquals("default", value);
        assertEquals(1, fallbackCalls.get());
    }

    @Test
    @DisplayName("memoize is lazy: creating it does not call the expensive supplier")
    void memoizeIsLazy() {
        AtomicInteger calls = new AtomicInteger();

        Exercise6_SupplierLaziness.memoize(() -> {
            calls.incrementAndGet();
            return "rates";
        });

        assertEquals(0, calls.get());
    }

    @Test
    @DisplayName("memoize calls the expensive supplier only once for many get() calls")
    void memoizeComputesOnce() {
        AtomicInteger calls = new AtomicInteger();
        Supplier<String> rates = Exercise6_SupplierLaziness.memoize(() -> "rates#" + calls.incrementAndGet());

        assertEquals("rates#1", rates.get());
        assertEquals("rates#1", rates.get());
        assertEquals("rates#1", rates.get());
        assertEquals(1, calls.get());
    }

    @Test
    @DisplayName("memoize also caches a null result")
    void memoizeCachesNull() {
        AtomicInteger calls = new AtomicInteger();
        Supplier<String> nothing = Exercise6_SupplierLaziness.memoize(() -> {
            calls.incrementAndGet();
            return null;
        });

        assertNull(nothing.get());
        assertNull(nothing.get());
        assertEquals(1, calls.get());
    }

    @Test
    @DisplayName("two memoized suppliers do not share their cache")
    void memoizeInstancesAreIndependent() {
        Supplier<String> a = Exercise6_SupplierLaziness.memoize(() -> "A");
        Supplier<String> b = Exercise6_SupplierLaziness.memoize(() -> "B");

        assertEquals("A", a.get());
        assertEquals("B", b.get());
    }

    @Test
    @DisplayName("sequentialIds gives a new id on every get()")
    void sequentialIdsCountUp() {
        Supplier<String> ids = Exercise6_SupplierLaziness.sequentialIds("ORD");

        assertEquals("ORD-1", ids.get());
        assertEquals("ORD-2", ids.get());
        assertEquals("ORD-3", ids.get());
    }

    @Test
    @DisplayName("each sequentialIds supplier has its own counter")
    void sequentialIdsAreIndependent() {
        Supplier<String> orders = Exercise6_SupplierLaziness.sequentialIds("ORD");
        Supplier<String> invoices = Exercise6_SupplierLaziness.sequentialIds("INV");

        assertEquals("ORD-1", orders.get());
        assertEquals("ORD-2", orders.get());
        assertEquals("INV-1", invoices.get());
    }

    @Test
    @DisplayName("logToBoth writes every message to both lists")
    void logToBothWritesBoth() {
        List<String> primary = new ArrayList<>();
        List<String> backup = new ArrayList<>();
        Consumer<String> log = Exercise6_SupplierLaziness.logToBoth(primary, backup);

        log.accept("started");
        log.accept("stopped");

        assertEquals(List.of("started", "stopped"), primary);
        assertEquals(List.of("started", "stopped"), backup);
    }

    @Test
    @DisplayName("logToBoth writes to the primary log before the backup log")
    void logToBothOrder() {
        List<String> trace = new ArrayList<>();
        // Lists that record into a shared trace whenever add() is called.
        List<String> primary = new ArrayList<>() {
            @Override
            public boolean add(String s) {
                trace.add("primary:" + s);
                return super.add(s);
            }
        };
        List<String> backup = new ArrayList<>() {
            @Override
            public boolean add(String s) {
                trace.add("backup:" + s);
                return super.add(s);
            }
        };

        Exercise6_SupplierLaziness.logToBoth(primary, backup).accept("x");

        assertEquals(List.of("primary:x", "backup:x"), trace);
    }
}
