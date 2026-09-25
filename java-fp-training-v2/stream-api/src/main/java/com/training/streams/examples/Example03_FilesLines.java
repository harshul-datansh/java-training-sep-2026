package com.training.streams.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Example 03 - Reading a file with Files.lines (and closing it!).
 *
 * <p>Idea: {@code Files.lines(path)} gives a lazy {@code Stream<String>} of the lines of a file.
 * It keeps the file OPEN until the stream is closed, and it does NOT close itself when the
 * terminal operation finishes. Always use try-with-resources.
 */
public class Example03_FilesLines {

    public static void main(String[] args) throws IOException {
        // Create a small temporary log file so the example is self-contained.
        Path log = Files.createTempFile("example03-", ".log");
        Files.write(log, List.of(
                "INFO  started",
                "ERROR database timeout",
                "INFO  request handled",
                "ERROR disk full"));

        // try-with-resources: the stream is closed automatically at the closing brace,
        // also when an exception is thrown inside the block. Stream implements AutoCloseable.
        try (Stream<String> lines = Files.lines(log)) {
            List<String> errors = lines
                    .filter(line -> line.startsWith("ERROR"))
                    .toList();
            System.out.println("error lines: " + errors);
            // prints [ERROR database timeout, ERROR disk full]
        }

        // WRONG (do not copy):  long n = Files.lines(log).count();
        // It gives the right number, but the file handle is never closed.
        // Do that on every request in a server and you leak file handles.

        Files.delete(log); // tidy up
        System.out.println("temp file deleted: " + !Files.exists(log)); // prints true
    }
}
