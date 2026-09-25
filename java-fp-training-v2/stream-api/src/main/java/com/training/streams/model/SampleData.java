package com.training.streams.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Shared sample data used by examples, exercises and tests.
 *
 * <p>The values are chosen on purpose, so every exercise has exactly one correct answer:
 * all product prices are different, there is one clear "most popular SKU", and every
 * order status appears at least once. All amounts are whole numbers or end in .5 / .25,
 * so sums and averages of {@code double}s come out exact (no 0.30000000000000004 surprises).
 *
 * <p>Every method returns an unmodifiable list ({@code List.of}).
 */
public final class SampleData {

    private SampleData() {
    }

    /** Eight products in three categories. Prices are all different. */
    public static List<Product> products() {
        return List.of(
                new Product("kb-100", "Mechanical Keyboard", "electronics", 120.0, true),
                new Product("ms-200", "Wireless Mouse", "electronics", 40.0, true),
                new Product("mn-300", "4K Monitor", "electronics", 360.0, false),
                new Product("dk-400", "Standing Desk", "furniture", 480.0, true),
                new Product("ch-500", "Office Chair", "furniture", 240.0, false),
                new Product("nb-600", "Notebook", "stationery", 4.0, true),
                new Product("pn-700", "Gel Pen", "stationery", 2.0, false),
                new Product("lp-800", "Desk Lamp", "furniture", 36.0, true));
    }

    /** Four articles; the last one has no tags at all. */
    public static List<Article> articles() {
        return List.of(
                new Article("Streams 101", List.of("java", "streams", "beginner")),
                new Article("Records in practice", List.of("java", "records")),
                new Article("Lambdas deep dive", List.of("lambdas", "java", "advanced")),
                new Article("No tags yet", List.of()));
    }

    /** Four simple orders; alice has two, and the order of carol has no lines. */
    public static List<Order> orders() {
        return List.of(
                new Order("o1", "alice", List.of(new OrderLine("KB", 2), new OrderLine("MS", 1))),
                new Order("o2", "bob", List.of(new OrderLine("MS", 3))),
                new Order("o3", "alice", List.of(new OrderLine("MN", 1), new OrderLine("KB", 1))),
                new Order("o4", "carol", List.of()));
    }

    /** Four invoices with different due dates; INV-1 is already paid. */
    public static List<Invoice> invoices() {
        return List.of(
                new Invoice("INV-1", LocalDate.of(2026, 1, 10), true),
                new Invoice("INV-2", LocalDate.of(2026, 2, 1), false),
                new Invoice("INV-3", LocalDate.of(2026, 1, 15), false),
                new Invoice("INV-4", LocalDate.of(2026, 3, 1), false));
    }

    /** Six employees in three departments. */
    public static List<Employee> employees() {
        return List.of(
                new Employee(1, "Cy", "Engineering", 85000),
                new Employee(2, "Ann", "Sales", 52000),
                new Employee(3, "Bo", "Engineering", 91000),
                new Employee(4, "Dan", "Finance", 60000),
                new Employee(5, "Eve", "Sales", 58000),
                new Employee(6, "Fay", "Engineering", 78000));
    }

    /**
     * Nine order DTOs for the end-to-end report.
     *
     * <ul>
     *   <li>Statuses: DELIVERED x5 (1, 3, 4, 6, 8), SHIPPED x2 (2, 9), PENDING x1 (7), CANCELLED x1 (5).</li>
     *   <li>SKU "MS-02" appears in 5 orders; no other SKU appears in more than 3.</li>
     *   <li>Order 4 is premium and delivered but only 99.5 (just under 100).</li>
     *   <li>Order 6 is delivered and over 100 but NOT premium.</li>
     *   <li>Dee has one CANCELLED order (5) and one SHIPPED order (9).</li>
     * </ul>
     */
    public static List<OrderDto> orderDtos() {
        return List.of(
                new OrderDto(1, "ann@acme.com", "Ann", true, 250.0, OrderStatus.DELIVERED, List.of("KB-01", "MS-02")),
                new OrderDto(2, "bo@acme.com", "Bo", false, 80.0, OrderStatus.SHIPPED, List.of("MS-02")),
                new OrderDto(3, "cy@globex.org", "Cy", true, 120.5, OrderStatus.DELIVERED, List.of("MN-03", "MS-02")),
                new OrderDto(4, "ann@acme.com", "Ann", true, 99.5, OrderStatus.DELIVERED, List.of("CB-04")),
                new OrderDto(5, "dee@globex.org", "Dee", true, 300.0, OrderStatus.CANCELLED, List.of("MN-03", "KB-01")),
                new OrderDto(6, "bo@acme.com", "Bo", false, 150.0, OrderStatus.DELIVERED, List.of("MS-02", "CB-04")),
                new OrderDto(7, "cy@globex.org", "Cy", true, 175.25, OrderStatus.PENDING, List.of("KB-01")),
                new OrderDto(8, "eva@initech.io", "Eva", true, 410.0, OrderStatus.DELIVERED, List.of("MN-03", "MS-02", "HD-05")),
                new OrderDto(9, "dee@globex.org", "Dee", false, 60.0, OrderStatus.SHIPPED, List.of("HD-05")));
    }
}
