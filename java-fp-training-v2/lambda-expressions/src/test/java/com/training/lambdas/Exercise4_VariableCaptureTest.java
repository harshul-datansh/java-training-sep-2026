package com.training.lambdas;

import com.training.lambdas.exercises.Exercise4_VariableCapture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Exercise4_VariableCaptureTest {

    @Test
    @DisplayName("priceWithDiscount captures the percentage")
    void priceWithDiscount() {
        Function<Double, Double> twentyOff = Exercise4_VariableCapture.priceWithDiscount(20);
        Function<Double, Double> halfPrice = Exercise4_VariableCapture.priceWithDiscount(50);
        assertEquals(80.0, twentyOff.apply(100.0), 0.0001);
        assertEquals(25.0, halfPrice.apply(50.0), 0.0001);
    }

    @Test
    @DisplayName("each ticket label supplier remembers its own number")
    void ticketLabels() {
        List<Supplier<String>> labels = Exercise4_VariableCapture.ticketLabels("A", 3);
        assertEquals(3, labels.size());
        assertEquals("A-1", labels.get(0).get());
        assertEquals("A-2", labels.get(1).get());
        assertEquals("A-3", labels.get(2).get());
    }

    @Test
    @DisplayName("ticketLabels with count 0 returns an empty list")
    void ticketLabelsEmpty() {
        assertTrue(Exercise4_VariableCapture.ticketLabels("B", 0).isEmpty());
    }

    @Test
    @DisplayName("liveItemCount reads the list size when get() is called")
    void liveItemCount() {
        List<String> cart = new ArrayList<>();
        Supplier<Integer> count = Exercise4_VariableCapture.liveItemCount(cart);
        assertEquals(0, count.get());

        cart.add("book");
        cart.add("pen");
        assertEquals(2, count.get());
    }
}
