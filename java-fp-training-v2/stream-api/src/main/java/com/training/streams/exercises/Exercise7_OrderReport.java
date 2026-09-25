package com.training.streams.exercises;

import com.training.streams.model.CustomerReport;
import com.training.streams.model.OrderDto;
import com.training.streams.model.OrderStatus;
import com.training.streams.model.OrderSummary;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Exercise 7 - End-to-end order report (hardest).
 *
 * <p>This is the kind of code you write in a real backend service: take a list of
 * {@link OrderDto}s from the database and turn it into response DTOs
 * ({@link OrderSummary}, {@link CustomerReport}) and statistics for an API or a dashboard.
 *
 * <p>Use {@link com.training.streams.model.SampleData#orderDtos()} to try things out.
 * Its Javadoc explains which orders are "traps". Examples:
 * <pre>
 *   premiumDeliveredOver(orders, 100.0)
 *       -> [OrderSummary[8, Eva, 410.0], OrderSummary[1, Ann, 250.0], OrderSummary[3, Cy, 120.5]]
 *   summariesByEmailDomain(orders)
 *       -> {acme.com=[orders 1, 2, 4, 6], globex.org=[orders 3, 7, 9], initech.io=[order 8]}
 *   customerReports(orders)
 *       -> first element: CustomerReport[ann@acme.com, 2, 349.5, 174.75]
 *   totalRevenue(orders)     -> 1345.25
 *   mostPopularSku(orders)   -> Optional[MS-02]
 *   countPerStatus(orders)   -> {PENDING=1, SHIPPED=2, DELIVERED=5, CANCELLED=1}
 * </pre>
 */
public class Exercise7_OrderReport {

    /**
     * Summaries of orders that are premium AND DELIVERED AND have a total strictly greater
     * than {@code minAmount}, sorted by total amount, highest first.
     */
    public List<OrderSummary> premiumDeliveredOver(List<OrderDto> orders, double minAmount) {
        // TODO: filter on three conditions, sort, convert to summaries.
        // Hint: OrderSummary has a static factory 'from(OrderDto)' that works as a method reference.
        //       Sort while you still have the OrderDto, or sort the summaries: both work, pick one.
        throw new UnsupportedOperationException("TODO: Exercise 7 - premiumDeliveredOver");
    }

    /**
     * Summaries of all orders that are NOT CANCELLED, grouped by the domain of the customer
     * e-mail (the part after '@'). Inside each group the summaries keep the input order.
     */
    public Map<String, List<OrderSummary>> summariesByEmailDomain(List<OrderDto> orders) {
        // TODO: remove cancelled orders, group by domain, turn each group into summaries.
        // Hint: the classifier can be any Function<OrderDto, String>, e.g. a lambda using
        //       substring and indexOf('@'). The downstream collector is the same idea as in
        //       Exercise 6 (namesByDepartment).
        throw new UnsupportedOperationException("TODO: Exercise 7 - summariesByEmailDomain");
    }

    /**
     * One {@link CustomerReport} per customer e-mail, counting only orders that are NOT CANCELLED,
     * sorted by e-mail alphabetically.
     *
     * <p>For Ann (orders 1 and 4): count 2, total 250.0 + 99.5 = 349.5, average 174.75.
     */
    public List<CustomerReport> customerReports(List<OrderDto> orders) {
        // TODO: group per e-mail, compute count / sum / average per group, build the reports, sort.
        // Hint: Collectors.summarizingDouble(...) gives count, sum and average in ONE pass.
        //       After collecting to a Map you can stream again over map.entrySet().
        throw new UnsupportedOperationException("TODO: Exercise 7 - customerReports");
    }

    /**
     * The sum of totalAmount of all orders that are NOT CANCELLED.
     */
    public double totalRevenue(List<OrderDto> orders) {
        // TODO: filter, then sum.
        // Hint: mapToDouble turns the stream into a DoubleStream, which has sum().
        throw new UnsupportedOperationException("TODO: Exercise 7 - totalRevenue");
    }

    /**
     * The SKU that occurs most often in the itemSkus of all orders (cancelled orders count too).
     *
     * @return the SKU, or {@code Optional.empty()} if there are no SKUs at all
     */
    public Optional<String> mostPopularSku(List<OrderDto> orders) {
        // TODO: flatten to SKUs, count per SKU, pick the entry with the highest count.
        // Hint: this is flatMap + groupingBy(..., counting()) + a SECOND stream over the entries.
        //       Map.Entry.comparingByValue() is a ready-made Comparator for max(...).
        throw new UnsupportedOperationException("TODO: Exercise 7 - mostPopularSku");
    }

    /**
     * How many orders there are in each status. Statuses that do not occur have no key.
     */
    public Map<OrderStatus, Long> countPerStatus(List<OrderDto> orders) {
        // TODO: group by status and count.
        // Hint: same collector combination as countByDepartment in Exercise 6.
        throw new UnsupportedOperationException("TODO: Exercise 7 - countPerStatus");
    }
}
