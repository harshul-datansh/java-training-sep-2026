package com.training.streams;

import com.training.streams.exercises.Exercise5_TerminalOperations;
import com.training.streams.model.Invoice;
import com.training.streams.model.Product;
import com.training.streams.model.SampleData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Exercise 5 - terminal operations")
class Exercise5_TerminalOperationsTest {

    private final Exercise5_TerminalOperations exercise = new Exercise5_TerminalOperations();
    private final List<Product> products = SampleData.products();

    private final List<Product> allInStock = List.of(
            new Product("a-1", "A", "x", 10.0, true),
            new Product("b-2", "B", "x", 20.0, true));

    @Test
    @DisplayName("anyOutOfStock is true for the sample catalogue")
    void anyOutOfStockTrue() {
        assertTrue(exercise.anyOutOfStock(products));
    }

    @Test
    @DisplayName("anyOutOfStock is false when everything is in stock")
    void anyOutOfStockFalse() {
        assertFalse(exercise.anyOutOfStock(allInStock));
    }

    @Test
    @DisplayName("allPricesPositive is true for the sample catalogue")
    void allPricesPositiveTrue() {
        assertTrue(exercise.allPricesPositive(products));
    }

    @Test
    @DisplayName("allPricesPositive is false when one price is 0")
    void allPricesPositiveFalse() {
        List<Product> withFreeItem = List.of(
                new Product("a-1", "A", "x", 10.0, true),
                new Product("f-0", "Free sticker", "x", 0.0, true));
        assertFalse(exercise.allPricesPositive(withFreeItem));
    }

    @Test
    @DisplayName("allPricesPositive is true for an empty list")
    void allPricesPositiveEmpty() {
        assertTrue(exercise.allPricesPositive(List.of()));
    }

    @Test
    @DisplayName("countByCategory counts products per category")
    void countByCategory() {
        assertEquals(3L, exercise.countByCategory(products, "furniture"));
        assertEquals(2L, exercise.countByCategory(products, "stationery"));
        assertEquals(0L, exercise.countByCategory(products, "garden"));
    }

    @Test
    @DisplayName("totalPrice adds all eight prices: 1282.0")
    void totalPrice() {
        // 120 + 40 + 360 + 480 + 240 + 4 + 2 + 36 = 1282
        assertEquals(1282.0, exercise.totalPrice(products));
    }

    @Test
    @DisplayName("totalPrice of an empty list is the identity value 0.0")
    void totalPriceEmpty() {
        assertEquals(0.0, exercise.totalPrice(List.of()));
    }

    @Test
    @DisplayName("firstOverdueInvoice returns the first unpaid invoice past its due date, in list order")
    void firstOverdue() {
        // On 2026-02-15 both INV-2 (due 02-01) and INV-3 (due 01-15) are overdue. INV-2 comes first in the list.
        assertEquals(Optional.of("INV-2"),
                exercise.firstOverdueInvoice(SampleData.invoices(), LocalDate.of(2026, 2, 15)));
    }

    @Test
    @DisplayName("firstOverdueInvoice skips paid invoices")
    void firstOverdueSkipsPaid() {
        // On 2026-01-20 INV-1 (paid) is past due but paid, INV-3 (due 01-15) is overdue.
        assertEquals(Optional.of("INV-3"),
                exercise.firstOverdueInvoice(SampleData.invoices(), LocalDate.of(2026, 1, 20)));
    }

    @Test
    @DisplayName("an invoice due today is not overdue yet")
    void dueTodayIsNotOverdue() {
        List<Invoice> invoices = List.of(new Invoice("INV-9", LocalDate.of(2026, 5, 1), false));
        assertEquals(Optional.empty(), exercise.firstOverdueInvoice(invoices, LocalDate.of(2026, 5, 1)));
    }

    @Test
    @DisplayName("firstOverdueInvoice is empty when nothing is overdue")
    void noneOverdue() {
        assertEquals(Optional.empty(),
                exercise.firstOverdueInvoice(SampleData.invoices(), LocalDate.of(2026, 1, 12)));
    }

    @Test
    @DisplayName("highestPrice returns the price of the Standing Desk: 480.0")
    void highestPrice() {
        assertEquals(Optional.of(480.0), exercise.highestPrice(products));
    }

    @Test
    @DisplayName("highestPrice is empty for an empty list")
    void highestPriceEmpty() {
        assertEquals(Optional.empty(), exercise.highestPrice(List.of()));
    }
}
