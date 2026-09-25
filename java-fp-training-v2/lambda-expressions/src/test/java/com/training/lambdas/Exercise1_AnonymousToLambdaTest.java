package com.training.lambdas;

import com.training.lambdas.exercises.Exercise1_AnonymousToLambda;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Exercise1_AnonymousToLambdaTest {

    @Test
    @DisplayName("byLength sorts shortest first")
    void byLengthSortsShortestFirst() {
        List<String> words = new ArrayList<>(List.of("banana", "fig", "apple"));
        words.sort(Exercise1_AnonymousToLambda.byLength());
        assertEquals(List.of("fig", "apple", "banana"), words);
    }

    @Test
    @DisplayName("ignoringCase sorts alphabetically regardless of case")
    void ignoringCaseSortsAlphabetically() {
        List<String> names = new ArrayList<>(List.of("bob", "Alice", "carol"));
        names.sort(Exercise1_AnonymousToLambda.ignoringCase());
        assertEquals(List.of("Alice", "bob", "carol"), names);
    }

    @Test
    @DisplayName("addToLog does nothing until run() is called")
    void addToLogIsDeferred() {
        List<String> log = new ArrayList<>();
        Runnable task = Exercise1_AnonymousToLambda.addToLog(log, "started");
        assertTrue(log.isEmpty(), "creating the Runnable must not run it");

        task.run();
        task.run();
        assertEquals(List.of("started", "started"), log);
    }
}
