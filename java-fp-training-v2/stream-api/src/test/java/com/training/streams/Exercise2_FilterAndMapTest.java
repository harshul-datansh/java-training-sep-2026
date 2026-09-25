package com.training.streams;

import com.training.streams.exercises.Exercise2_FilterAndMap;
import com.training.streams.model.Product;
import com.training.streams.model.SampleData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exercise 2 - filter and map")
class Exercise2_FilterAndMapTest {

    private final Exercise2_FilterAndMap exercise = new Exercise2_FilterAndMap();
    private final List<Product> products = SampleData.products();

    @Test
    @DisplayName("namesInStockUnder(100) returns in-stock products under 100, in input order")
    void namesInStockUnder100() {
        // Gel Pen (2.0) is cheap but out of stock, so it must not appear.
        assertEquals(List.of("Wireless Mouse", "Notebook", "Desk Lamp"),
                exercise.namesInStockUnder(products, 100.0));
    }

    @Test
    @DisplayName("namesInStockUnder uses a strict limit: a price equal to the limit is excluded")
    void namesInStockUnderIsStrict() {
        // Mechanical Keyboard costs exactly 120.0
        assertEquals(List.of("Wireless Mouse", "Notebook", "Desk Lamp"),
                exercise.namesInStockUnder(products, 120.0));
        assertEquals(List.of("Mechanical Keyboard", "Wireless Mouse", "Notebook", "Desk Lamp"),
                exercise.namesInStockUnder(products, 120.5));
    }

    @Test
    @DisplayName("namesInStockUnder returns an empty list when nothing matches")
    void namesInStockUnderNothing() {
        assertEquals(List.of(), exercise.namesInStockUnder(products, 1.0));
    }

    @Test
    @DisplayName("upperCaseSkusOfCategory(furniture) returns upper-case SKUs in input order")
    void upperCaseSkusFurniture() {
        assertEquals(List.of("DK-400", "CH-500", "LP-800"),
                exercise.upperCaseSkusOfCategory(products, "furniture"));
    }

    @Test
    @DisplayName("upperCaseSkusOfCategory(electronics) returns the three electronics SKUs")
    void upperCaseSkusElectronics() {
        assertEquals(List.of("KB-100", "MS-200", "MN-300"),
                exercise.upperCaseSkusOfCategory(products, "electronics"));
    }

    @Test
    @DisplayName("upperCaseSkusOfCategory with an unknown category returns an empty list")
    void upperCaseSkusUnknownCategory() {
        assertEquals(List.of(), exercise.upperCaseSkusOfCategory(products, "garden"));
    }

    @Test
    @DisplayName("pricesWithVat(0.25) adds 25 % to every price")
    void pricesWithVat25() {
        assertEquals(List.of(150.0, 50.0, 450.0, 600.0, 300.0, 5.0, 2.5, 45.0),
                exercise.pricesWithVat(products, 0.25));
    }

    @Test
    @DisplayName("pricesWithVat(0.0) returns the prices unchanged")
    void pricesWithVatZero() {
        assertEquals(List.of(120.0, 40.0, 360.0, 480.0, 240.0, 4.0, 2.0, 36.0),
                exercise.pricesWithVat(products, 0.0));
    }
}
