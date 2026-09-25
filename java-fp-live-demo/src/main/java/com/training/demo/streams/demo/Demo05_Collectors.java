package com.training.demo.streams.demo;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;
import com.training.demo.model.SampleData;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * DEMO 5 - Collectors: turning a stream back into a List, Map or String.
 */
public class Demo05_Collectors {

    public static void main(String[] args) {
        List<Order> orders = SampleData.orders();

        // toList: "give me the ids of orders to ship"
        List<String> toShip = orders.stream()
                .filter(Order::isPaid)
                .map(Order::id)
                .collect(Collectors.toList());   // since Java 16 you can also write .toList()
        System.out.println("toList    - paid order ids to ship : " + toShip);
        System.out.println();

        // toMap: "look up an order total by id" (ids are unique -> safe)
        Map<String, Double> totalById = orders.stream()
                .collect(Collectors.toMap(Order::id, Order::total));
        System.out.println("toMap     - total of A-102         : " + totalById.get("A-102"));

        // toMap with DUPLICATE keys fails: bob has two orders.
        try {
            orders.stream().collect(Collectors.toMap(Order::customerEmail, Order::total));
        } catch (IllegalStateException e) {
            System.out.println("toMap     - by email, no merge     : IllegalStateException -> " + e.getMessage());
        }
        // Fix: tell toMap how to MERGE two values for the same key (here: add them up).
        Map<String, Double> spendByCustomer = orders.stream()
                .collect(Collectors.toMap(Order::customerEmail, Order::total, Double::sum, TreeMap::new));
        System.out.println("toMap     - by email, merge = sum  : " + spendByCustomer);
        System.out.println();

        // groupingBy: "orders per status" for the ops dashboard
        Map<OrderStatus, List<String>> idsByStatus = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::status,                                        // the key
                        TreeMap::new,                                         // map type (sorted keys for display)
                        Collectors.mapping(Order::id, Collectors.toList()))); // what to keep per key
        System.out.println("groupingBy - ids per status        : " + idsByStatus);

        // groupingBy + counting: "how many per status"
        Map<OrderStatus, Long> countByStatus = orders.stream()
                .collect(Collectors.groupingBy(Order::status, TreeMap::new, Collectors.counting()));
        System.out.println("groupingBy - count per status      : " + countByStatus);
        System.out.println();

        // joining: "the To: line for the premium newsletter"
        String toLine = orders.stream()
                .filter(Order::premium)
                .map(Order::customerEmail)
                .distinct()
                .collect(Collectors.joining(", ", "To: ", ";"));
        System.out.println("joining   - newsletter recipients  : " + toLine);
    }
}
