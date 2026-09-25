package com.training.streams.solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** Solution for Exercise 1 - Creating streams. */
public class Exercise1_CreatingStreams {

    public List<Integer> firstNSquares(int n) {
        // rangeClosed includes the end value (range(1, n) would stop at n - 1: an off-by-one trap).
        // map works on the primitive ints; boxed() turns IntStream into Stream<Integer> so that
        // toList() can produce a List<Integer>. For n = 0 the range is empty -> empty list.
        return IntStream.rangeClosed(1, n)
                .map(i -> i * i)
                .boxed()
                .toList();
    }

    public List<Integer> powersOfTwo(int count) {
        // iterate(1, x -> x * 2) produces 1, 2, 4, 8, ... forever. limit(count) is what makes it finite.
        // Laziness is why this works: only 'count' elements are ever computed.
        return Stream.iterate(1, x -> x * 2)
                .limit(count)
                .toList();
    }

    public List<String> fromValues(String... values) {
        // Stream.of(T...) takes the array directly. Arrays.stream(values) would work just as well.
        return Stream.of(values).toList();
    }

    public long countLinesContaining(Path file, String word) throws IOException {
        // Files.lines reads lazily and holds an open file handle. Stream implements AutoCloseable,
        // so try-with-resources closes the handle when we leave the block, even on an exception.
        // Without it the file handle leaks: it stays open for an unknown time, and a busy
        // server that does this on every request can run out of file handles.
        try (Stream<String> lines = Files.lines(file)) {
            return lines.filter(line -> line.contains(word)).count();
        }
    }
}
