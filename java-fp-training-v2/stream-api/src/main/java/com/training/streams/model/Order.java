package com.training.streams.model;

import java.util.List;

/**
 * A simple order with several {@link OrderLine}s. Used in the flatMap exercise.
 *
 * @param id       order id, for example "o1"
 * @param customer customer username, for example "alice"
 * @param lines    the order lines (may be empty)
 */
public record Order(String id, String customer, List<OrderLine> lines) {
}
