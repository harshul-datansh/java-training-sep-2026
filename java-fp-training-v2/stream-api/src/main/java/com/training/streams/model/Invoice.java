package com.training.streams.model;

import java.time.LocalDate;

/**
 * An invoice sent to a customer.
 *
 * @param id      invoice number, for example "INV-2"
 * @param dueDate the last day the invoice can be paid on time
 * @param paid    true if the invoice has been paid
 */
public record Invoice(String id, LocalDate dueDate, boolean paid) {
}
