package com.training.streams.exercises;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Exercise 1 - Creating streams (easiest).
 *
 * <p>Every stream starts with a SOURCE. In this exercise you practise four different sources:
 * a number range, {@code Stream.iterate}, a varargs array and the lines of a file.
 *
 * <p>Examples:
 * <pre>
 *   firstNSquares(4)        -> [1, 4, 9, 16]
 *   powersOfTwo(5)          -> [1, 2, 4, 8, 16]
 *   fromValues("a", "b")    -> ["a", "b"]
 *   countLinesContaining(file with lines "error: disk", "ok", "error: net", "Error: CPU"), "error")
 *                           -> 2   (the check is case-sensitive)
 * </pre>
 *
 * <p>Run the tests: {@code mvn test -pl stream-api -Dtest=Exercise1_CreatingStreamsTest}
 */
public class Exercise1_CreatingStreams {

    /**
     * Returns the squares of 1, 2, ..., n in order.
     *
     * @param n how many squares (0 gives an empty list)
     * @return for n = 4: [1, 4, 9, 16]
     */
    public List<Integer> firstNSquares(int n) {
        // TODO: build the list with a stream, not a for loop.
        // Hint: IntStream.rangeClosed(...) gives you 1..n. An IntStream holds primitive ints,
        //       so you need boxed() before you can collect into a List<Integer>.
        throw new UnsupportedOperationException("TODO: Exercise 1 - firstNSquares");
    }

    /**
     * Returns the first {@code count} powers of two, starting at 1 (2 to the power 0).
     *
     * @param count how many numbers (0 gives an empty list)
     * @return for count = 5: [1, 2, 4, 8, 16]
     */
    public List<Integer> powersOfTwo(int count) {
        // TODO: generate the numbers with Stream.iterate.
        // Hint: Stream.iterate(seed, nextFunction) is INFINITE. Something must stop it
        //       (look at limit), otherwise collect never returns.
        throw new UnsupportedOperationException("TODO: Exercise 1 - powersOfTwo");
    }

    /**
     * Turns the given values into a list, keeping their order.
     *
     * @param values any number of strings
     * @return fromValues("x", "y", "z") gives ["x", "y", "z"]
     */
    public List<String> fromValues(String... values) {
        // TODO: create a stream from the varargs and collect it.
        // Hint: a varargs parameter is just an array. Stream.of(...) or Arrays.stream(...) both accept one.
        throw new UnsupportedOperationException("TODO: Exercise 1 - fromValues");
    }

    /**
     * Counts the lines in a text file that contain {@code word} (case-sensitive).
     *
     * @param file a UTF-8 text file
     * @param word the text to look for
     * @return the number of matching lines
     * @throws IOException if the file cannot be read
     */
    public long countLinesContaining(Path file, String word) throws IOException {
        // TODO: read the file as a stream of lines and count the matching ones.
        // Hint: Files.lines(file) keeps the file OPEN until the stream is closed.
        //       A try-with-resources block closes it for you, even when an exception happens.
        throw new UnsupportedOperationException("TODO: Exercise 1 - countLinesContaining");
    }
}
