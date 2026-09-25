package com.training.demo.streams.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * DEMO 2 - Where streams come from.
 */
public class Demo02_StreamSources {

    public static void main(String[] args) throws IOException {
        // 1) From any Collection: list.stream()
        List<Order> orders = SampleData.orders();
        System.out.println("1) collection.stream()  : "
                + orders.stream().map(Order::id).collect(Collectors.toList()));

        // 2) From a few values: Stream.of(...)
        System.out.println("2) Stream.of(...)       : "
                + Stream.of("NEW", "PAID", "SHIPPED").map(String::toLowerCase).toList());

        // 3) From an array: Arrays.stream(array)
        double[] dailyRevenue = {1200.0, 850.5, 990.0};
        System.out.println("3) Arrays.stream(array) : total " + Arrays.stream(dailyRevenue).sum());

        // 4) A range of numbers: IntStream.range(start, endExclusive)
        System.out.println("4) IntStream.range(1, 4): "
                + IntStream.range(1, 4).mapToObj(i -> "batch-" + i).toList());

        // 5) Lines of a file: Files.lines(path)
        //    The stream keeps the file OPEN, so always use try-with-resources to close it.
        Path csv = Files.createTempFile("orders", ".csv");
        Files.writeString(csv, "A-100;anna@shop.test;120.00\nA-101;bob@shop.test;35.50\nA-102;carla@shop.test;560.00\n");
        try (Stream<String> lines = Files.lines(csv)) {
            List<String> ids = lines.map(line -> line.split(";")[0]).toList();
            System.out.println("5) Files.lines(path)    : " + ids + "   (file closed automatically)");
        }
        Files.delete(csv);

        // 6) Generated values: Stream.iterate(seed, next) is INFINITE - limit(...) makes it stop.
        System.out.println("6) Stream.iterate+limit : "
                + Stream.iterate(200, n -> n + 1).limit(3).map(n -> "A-" + n).toList());
    }
}
