package com.training.streams;

import com.training.streams.exercises.Exercise7_OrderReport;
import com.training.streams.model.CustomerReport;
import com.training.streams.model.OrderDto;
import com.training.streams.model.OrderStatus;
import com.training.streams.model.OrderSummary;
import com.training.streams.model.SampleData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exercise 7 - End-to-end order report")
class Exercise7_OrderReportTest {

    private final Exercise7_OrderReport exercise = new Exercise7_OrderReport();
    private final List<OrderDto> orders = SampleData.orderDtos();

    @Test
    @DisplayName("premiumDeliveredOver(100) returns orders 8, 1, 3, highest total first")
    void premiumDeliveredOver100() {
        // Order 4 is premium+delivered but 99.5. Order 6 is delivered and 150 but not premium.
        // Order 5 is premium and 300 but CANCELLED. Order 7 is premium and 175.25 but PENDING.
        assertEquals(List.of(
                        new OrderSummary(8, "Eva", 410.0),
                        new OrderSummary(1, "Ann", 250.0),
                        new OrderSummary(3, "Cy", 120.5)),
                exercise.premiumDeliveredOver(orders, 100.0));
    }

    @Test
    @DisplayName("premiumDeliveredOver uses a strict limit (order 3 at 120.5 is excluded at 120.5)")
    void premiumDeliveredOverIsStrict() {
        assertEquals(List.of(
                        new OrderSummary(8, "Eva", 410.0),
                        new OrderSummary(1, "Ann", 250.0)),
                exercise.premiumDeliveredOver(orders, 120.5));
    }

    @Test
    @DisplayName("summariesByEmailDomain groups non-cancelled orders by e-mail domain")
    void summariesByDomain() {
        assertEquals(Map.of(
                        "acme.com", List.of(
                                new OrderSummary(1, "Ann", 250.0),
                                new OrderSummary(2, "Bo", 80.0),
                                new OrderSummary(4, "Ann", 99.5),
                                new OrderSummary(6, "Bo", 150.0)),
                        "globex.org", List.of(
                                new OrderSummary(3, "Cy", 120.5),
                                new OrderSummary(7, "Cy", 175.25),
                                new OrderSummary(9, "Dee", 60.0)),
                        "initech.io", List.of(
                                new OrderSummary(8, "Eva", 410.0))),
                exercise.summariesByEmailDomain(orders));
    }

    @Test
    @DisplayName("customerReports returns one report per customer, sorted by e-mail, cancelled orders ignored")
    void customerReports() {
        assertEquals(List.of(
                        new CustomerReport("ann@acme.com", 2, 349.5, 174.75),
                        new CustomerReport("bo@acme.com", 2, 230.0, 115.0),
                        new CustomerReport("cy@globex.org", 2, 295.75, 147.875),
                        new CustomerReport("dee@globex.org", 1, 60.0, 60.0),
                        new CustomerReport("eva@initech.io", 1, 410.0, 410.0)),
                exercise.customerReports(orders));
    }

    @Test
    @DisplayName("customerReports of no orders is an empty list")
    void customerReportsEmpty() {
        assertEquals(List.of(), exercise.customerReports(List.of()));
    }

    @Test
    @DisplayName("totalRevenue sums all non-cancelled orders: 1345.25")
    void totalRevenue() {
        // 250 + 80 + 120.5 + 99.5 + 150 + 175.25 + 410 + 60 = 1345.25 (order 5, 300.0, is cancelled)
        assertEquals(1345.25, exercise.totalRevenue(orders));
    }

    @Test
    @DisplayName("mostPopularSku is MS-02 (in 5 orders; no other SKU is in more than 3)")
    void mostPopularSku() {
        assertEquals(Optional.of("MS-02"), exercise.mostPopularSku(orders));
    }

    @Test
    @DisplayName("mostPopularSku picks the SKU with the highest count in a small list")
    void mostPopularSkuSmall() {
        List<OrderDto> small = List.of(
                new OrderDto(1, "a@x.com", "A", false, 10.0, OrderStatus.PENDING, List.of("A", "B")),
                new OrderDto(2, "b@x.com", "B", false, 10.0, OrderStatus.PENDING, List.of("B")),
                new OrderDto(3, "c@x.com", "C", false, 10.0, OrderStatus.PENDING, List.of("C", "B", "A")));
        assertEquals(Optional.of("B"), exercise.mostPopularSku(small));
    }

    @Test
    @DisplayName("mostPopularSku is empty when there are no SKUs")
    void mostPopularSkuEmpty() {
        assertEquals(Optional.empty(), exercise.mostPopularSku(List.of()));
    }

    @Test
    @DisplayName("countPerStatus has exactly the four statuses with the right counts")
    void countPerStatus() {
        Map<OrderStatus, Long> counts = exercise.countPerStatus(orders);
        assertEquals(4, counts.size());
        assertEquals(Map.of(
                        OrderStatus.PENDING, 1L,
                        OrderStatus.SHIPPED, 2L,
                        OrderStatus.DELIVERED, 5L,
                        OrderStatus.CANCELLED, 1L),
                counts);
    }

    @Test
    @DisplayName("countPerStatus has no key for a status that does not occur")
    void countPerStatusMissingStatus() {
        List<OrderDto> twoStatuses = List.of(
                new OrderDto(1, "a@x.com", "A", false, 10.0, OrderStatus.PENDING, List.of()),
                new OrderDto(2, "b@x.com", "B", false, 10.0, OrderStatus.SHIPPED, List.of()),
                new OrderDto(3, "c@x.com", "C", false, 10.0, OrderStatus.PENDING, List.of()));
        assertEquals(Map.of(OrderStatus.PENDING, 2L, OrderStatus.SHIPPED, 1L),
                exercise.countPerStatus(twoStatuses));
    }
}
