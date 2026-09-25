package com.training.streams.examples;

import com.training.streams.model.OrderDto;
import com.training.streams.model.OrderStatus;
import com.training.streams.model.OrderSummary;
import com.training.streams.model.SampleData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Example 13 - An end-to-end backend task.
 *
 * <p>Idea: a service method receives order DTOs (from a repository) and must return response
 * DTOs for an API: filter, transform, sort, group and summarise, all with streams.
 */
public class Example13_EndToEndOrderReport {

    public static void main(String[] args) {
        List<OrderDto> orders = SampleData.orderDtos();

        // 1) "GET /orders/delivered": delivered orders as summaries, biggest first.
        List<OrderSummary> delivered = orders.stream()
                .filter(order -> order.status() == OrderStatus.DELIVERED)   // keep what the API asks for
                .sorted(Comparator.comparingDouble(OrderDto::totalAmount).reversed())
                .map(OrderSummary::from)                                    // DTO in -> response DTO out
                .toList();
        System.out.println("1) delivered, biggest first:");
        delivered.forEach(summary -> System.out.println("   " + summary));

        // 2) Dashboard: revenue per customer name, ignoring cancelled orders.
        Map<String, Double> revenueByCustomer = orders.stream()
                .filter(order -> order.status() != OrderStatus.CANCELLED)
                .collect(Collectors.groupingBy(
                        OrderDto::customerName,
                        TreeMap::new,                                           // sorted keys for stable output
                        Collectors.summingDouble(OrderDto::totalAmount)));
        System.out.println("2) revenue per customer: " + revenueByCustomer);

        // 3) Dashboard: number of orders per status.
        Map<OrderStatus, Long> perStatus = orders.stream()
                .collect(Collectors.groupingBy(OrderDto::status, TreeMap::new, Collectors.counting()));
        System.out.println("3) orders per status   : " + perStatus);

        // 4) One line for a log message: ids of premium orders.
        String premiumIds = orders.stream()
                .filter(OrderDto::premium)
                .map(order -> String.valueOf(order.id()))
                .collect(Collectors.joining(", ", "premium orders: [", "]"));
        System.out.println("4) " + premiumIds);
    }
}
