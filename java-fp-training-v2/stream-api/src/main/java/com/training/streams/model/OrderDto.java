package com.training.streams.model;

import java.util.List;

/**
 * An order as it might arrive from a database or another service.
 * This is the INPUT of the order report exercise.
 *
 * @param id            order id
 * @param customerEmail customer e-mail, for example "ann@acme.com"
 * @param customerName  customer display name
 * @param premium       true if the customer has a premium membership
 * @param totalAmount   order total
 * @param status        where the order is in its life cycle
 * @param itemSkus      the SKUs of the items in the order
 */
public record OrderDto(long id,
                       String customerEmail,
                       String customerName,
                       boolean premium,
                       double totalAmount,
                       OrderStatus status,
                       List<String> itemSkus) {
}
