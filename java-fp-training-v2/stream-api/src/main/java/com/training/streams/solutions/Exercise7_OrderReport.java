package com.training.streams.solutions;

import com.training.streams.model.CustomerReport;
import com.training.streams.model.OrderDto;
import com.training.streams.model.OrderStatus;
import com.training.streams.model.OrderSummary;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/** Solution for Exercise 7 - End-to-end order report. */
public class Exercise7_OrderReport {

    public List<OrderSummary> premiumDeliveredOver(List<OrderDto> orders, double minAmount) {
        // Three small filters read like the requirement, and each can be removed or changed alone.
        // Order 4 (99.5) fails the amount check and order 6 fails the premium check.
        // Sorting the summaries (not the DTOs) means the comparator uses the response field we return.
        return orders.stream()
                .filter(OrderDto::premium)
                .filter(order -> order.status() == OrderStatus.DELIVERED)
                .filter(order -> order.totalAmount() > minAmount)
                .map(OrderSummary::from)
                .sorted(Comparator.comparingDouble(OrderSummary::totalAmount).reversed())
                .toList();
    }

    public Map<String, List<OrderSummary>> summariesByEmailDomain(List<OrderDto> orders) {
        // Enums are compared with '!=' / '==' safely (one instance per constant).
        // The classifier computes the key; mapping(...) converts each OrderDto inside its group,
        // so we never build a Map<String, List<OrderDto>> first and convert it afterwards.
        return orders.stream()
                .filter(order -> order.status() != OrderStatus.CANCELLED)
                .collect(Collectors.groupingBy(
                        Exercise7_OrderReport::emailDomain,
                        Collectors.mapping(OrderSummary::from, Collectors.toList())));
    }

    public List<CustomerReport> customerReports(List<OrderDto> orders) {
        // Step 1: one pass that groups by e-mail and computes count, sum and average per group.
        //         summarizingDouble avoids three separate groupingBy calls.
        Map<String, DoubleSummaryStatistics> statsByEmail = orders.stream()
                .filter(order -> order.status() != OrderStatus.CANCELLED)
                .collect(Collectors.groupingBy(
                        OrderDto::customerEmail,
                        Collectors.summarizingDouble(OrderDto::totalAmount)));

        // Step 2: a NEW stream over the map entries turns each group into a response DTO.
        // A HashMap has no useful order, so we sort explicitly by e-mail at the end.
        return statsByEmail.entrySet().stream()
                .map(entry -> new CustomerReport(
                        entry.getKey(),
                        entry.getValue().getCount(),
                        entry.getValue().getSum(),
                        entry.getValue().getAverage()))
                .sorted(Comparator.comparing(CustomerReport::customerEmail))
                .toList();
    }

    public double totalRevenue(List<OrderDto> orders) {
        // mapToDouble + sum avoids boxing and returns 0.0 for no orders.
        // Excluding CANCELLED matters: order 5 (300.0) would otherwise inflate the revenue.
        return orders.stream()
                .filter(order -> order.status() != OrderStatus.CANCELLED)
                .mapToDouble(OrderDto::totalAmount)
                .sum();
    }

    public Optional<String> mostPopularSku(List<OrderDto> orders) {
        // Stream 1: OrderDto -> many SKUs (flatMap) -> Map<sku, count>.
        // Stream 2: over the entries, max by count, then keep only the key.
        // The Optional is empty when there are no SKUs at all, so no special case is needed.
        // (If two SKUs tied, max would return one of them; the sample data has a unique winner.)
        Map<String, Long> countBySku = orders.stream()
                .flatMap(order -> order.itemSkus().stream())
                .collect(Collectors.groupingBy(sku -> sku, Collectors.counting()));

        return countBySku.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public Map<OrderStatus, Long> countPerStatus(List<OrderDto> orders) {
        // groupingBy only creates keys that actually occur: a status with 0 orders is absent,
        // not mapped to 0. The caller must use getOrDefault(status, 0L) if it needs zeros.
        return orders.stream()
                .collect(Collectors.groupingBy(OrderDto::status, Collectors.counting()));
    }

    /** "ann@acme.com" -> "acme.com". A private helper keeps the lambda in the pipeline short. */
    private static String emailDomain(OrderDto order) {
        String email = order.customerEmail();
        return email.substring(email.indexOf('@') + 1);
    }
}
