package com.training.demo.streams.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.OrderStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * EXERCISE 2 (medium) - flatMap, distinct, sorted, reduce.
 *
 * Run the test:  mvn test -Dtest=Ex02_FlatMapAndReduceTest
 */
public class Ex02_FlatMapAndReduce {

    /**
     * Every SKU that appears in any order, each only once, in alphabetical order.
     * The warehouse uses this as its pick list.
     *
     * TODO: write a stream pipeline.
     *
     * Hint: each order has a LIST of SKUs. map would give you a stream of lists;
     * you want one flat stream of SKUs instead.
     */
    public static List<String> distinctSkusSorted(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.skus().stream())
                .sorted()
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Total revenue: the sum of all totals, EXCLUDING cancelled orders.
     * An empty list gives 0.0.
     *
     * TODO: write a stream pipeline that ends with reduce.
     *
     * Hint: reduce needs a starting value and a way to combine two numbers into one.
     * Which starting value makes an empty list come out right?
     */
    public static double revenue(List<Order> orders) {
    /*    List<Order> list=  orders.stream()
                .filter(Order-> !OrderStatus.CANCELLED.equals(Order.status()))
                .toList();
        Double sum = 0.0;
        for (Order order : list){
            sum+=order.total();
        }
        return sum;

     */
        return orders.stream()
                .filter(Order -> !OrderStatus.CANCELLED.equals(Order.status()))
                .map(Order::total)
                .reduce(0.0,Double::sum);


    }
}
