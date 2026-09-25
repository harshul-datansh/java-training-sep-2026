package com.training.demo.model;

import java.util.List;

/** The same small, predictable data set used by every demo. */
public final class SampleData {

    private SampleData() {
    }

    public static List<Order> orders() {
        return List.of(
                new Order("A-100", "anna@shop.test",  true,  120.00, OrderStatus.PAID,      List.of("BOOK-1", "PEN-2")),
                new Order("A-101", "bob@shop.test",   false,  35.50, OrderStatus.NEW,       List.of("PEN-2")),
                new Order("A-102", "carla@shop.test", true,  560.00, OrderStatus.SHIPPED,   List.of("LAPTOP-9", "BAG-3")),
                new Order("A-103", "bob@shop.test",   false,  89.99, OrderStatus.PAID,      List.of("BOOK-1", "BOOK-7", "PEN-2")),
                new Order("A-104", "dev@shop.test",   false,  15.00, OrderStatus.CANCELLED, List.of("MUG-4")),
                new Order("A-105", "anna@shop.test",  true,  240.00, OrderStatus.PAID,      List.of("HEADSET-5")),
                new Order("A-106", "erik@shop.test",  false, 999.00, OrderStatus.NEW,       List.of("LAPTOP-9"))
        );
    }
}
