package com.training.streams;

import com.training.streams.exercises.Exercise1_CreatingStreams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exercise 1 - Creating streams")
class Exercise1_CreatingStreamsTest {

    private final Exercise1_CreatingStreams exercise = new Exercise1_CreatingStreams();

    @Test
    @DisplayName("firstNSquares(5) returns the squares of 1..5 in order")
    void firstNSquaresOfFive() {
        assertEquals(List.of(1, 4, 9, 16, 25), exercise.firstNSquares(5));
    }

    @Test
    @DisplayName("firstNSquares(1) includes n itself (rangeClosed, not range)")
    void firstNSquaresIncludesN() {
        assertEquals(List.of(1), exercise.firstNSquares(1));
    }

    @Test
    @DisplayName("firstNSquares(0) returns an empty list")
    void firstNSquaresOfZero() {
        assertEquals(List.of(), exercise.firstNSquares(0));
    }

    @Test
    @DisplayName("powersOfTwo(6) returns 1, 2, 4, 8, 16, 32")
    void powersOfTwoSix() {
        assertEquals(List.of(1, 2, 4, 8, 16, 32), exercise.powersOfTwo(6));
    }

    @Test
    @DisplayName("powersOfTwo(0) returns an empty list")
    void powersOfTwoZero() {
        assertEquals(List.of(), exercise.powersOfTwo(0));
    }

    @Test
    @DisplayName("fromValues keeps the order of the values")
    void fromValuesKeepsOrder() {
        assertEquals(List.of("c", "a", "b"), exercise.fromValues("c", "a", "b"));
    }

    @Test
    @DisplayName("fromValues with no arguments returns an empty list")
    void fromValuesEmpty() {
        assertEquals(List.of(), exercise.fromValues());
    }

    @Test
    @DisplayName("countLinesContaining counts matching lines, case-sensitive")
    void countLinesContaining(@TempDir Path dir) throws IOException {
        Path log = dir.resolve("app.log");
        Files.write(log, List.of("error: disk full", "ok", "error: network down", "Error: CPU hot", "all good"));

        assertEquals(2L, exercise.countLinesContaining(log, "error"));
    }

    @Test
    @DisplayName("countLinesContaining returns 0 when nothing matches")
    void countLinesContainingNoMatch(@TempDir Path dir) throws IOException {
        Path log = dir.resolve("quiet.log");
        Files.write(log, List.of("ok", "ok", "ok"));

        assertEquals(0L, exercise.countLinesContaining(log, "error"));
    }
}
