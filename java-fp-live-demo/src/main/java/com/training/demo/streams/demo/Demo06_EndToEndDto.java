package com.training.demo.streams.demo;

import com.training.demo.model.Order;
import com.training.demo.model.OrderDto;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * DEMO 6 - End to end: the "orders overview" endpoint of our backend.
 *
 * Requirement:
 *   - skip cancelled orders
 *   - convert each Order into an OrderDto (total incl. 25% VAT, rounded to cents; item count)
 *   - group the DTOs by customer tier: "PREMIUM" / "STANDARD"
 *   - inside each group, most expensive first
 */
public class Demo06_EndToEndDto {

    private static final double VAT = 0.25;

    public static void main(String[] args) {
        List<Order> orders = SampleData.orders();

        // ---------------- BEFORE: loops and a hand-built map ----------------
        Map<String, List<OrderDto>> before = new TreeMap<>();
        for (Order o : orders) {
            if (o.status() == OrderStatus.CANCELLED) {
                continue;
            }
            OrderDto dto = toDto(o);
            String tier = o.premium() ? "PREMIUM" : "STANDARD";
            before.computeIfAbsent(tier, k -> new ArrayList<>()).add(dto);
        }
        for (List<OrderDto> group : before.values()) {
            group.sort(Comparator.comparingDouble(OrderDto::totalWithVat).reversed());
        }

        // ---------------- AFTER: one pipeline that reads like the requirement ----------------
        Map<String, List<OrderDto>> after = orders.stream()
                .filter(o -> o.status() != OrderStatus.CANCELLED)                       // skip cancelled
                .sorted(Comparator.comparingDouble(Order::total).reversed())            // most expensive first
                .collect(Collectors.groupingBy(
                        o -> o.premium() ? "PREMIUM" : "STANDARD",                      // group key
                        TreeMap::new,                                                   // sorted keys for display
                        Collectors.mapping(Demo06_EndToEndDto::toDto, Collectors.toList()))); // Order -> OrderDto

        System.out.println("=== Orders overview (stream version) ===");
        after.forEach((tier, dtos) -> {
            System.out.println(tier);
            dtos.forEach(d -> System.out.printf("  %s  %-16s %8.2f  items=%d%n",
                    d.id(), d.customer(), d.totalWithVat(), d.itemCount()));
        });
        System.out.println();
        System.out.println("loop version gives the same result? " + before.equals(after));
    }

    /** Mapping logic lives in a named method, not in a fat lambda. */
    static OrderDto toDto(Order o) {
        double withVat = Math.round(o.total() * (1 + VAT) * 100) / 100.0;
        return new OrderDto(o.id(), o.customerEmail(), withVat, o.skus().size());
    }
}
