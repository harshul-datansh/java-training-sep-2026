package com.training.demo.lambdas.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Ex03_MethodReferencesTest {

    private final Order a100 = SampleData.orders().get(0); // BOOK-1, PEN-2

    @Test
    void parseQuantityTurnsTextIntoNumber() {
        assertEquals(3, Ex03_MethodReferences.parseQuantity().apply("3"));
        assertEquals(42, Ex03_MethodReferences.parseQuantity().apply("42"));
    }

    @Test
    void isSkuInChecksThatParticularOrder() {
        Predicate<String> inA100 = Ex03_MethodReferences.isSkuIn(a100);
        assertTrue(inA100.test("BOOK-1"));
        assertFalse(inA100.test("LAPTOP-9"));
    }

    @Test
    void idOfWorksForAnyOrder() {
        List<String> ids = SampleData.orders().stream().map(Ex03_MethodReferences.idOf()).toList();
        assertEquals("A-100", ids.get(0));
        assertEquals("A-106", ids.get(6));
    }

    @Test
    void newSkuListGivesFreshModifiableLists() {
        Supplier<List<String>> factory = Ex03_MethodReferences.newSkuList();
        List<String> first = factory.get();
        List<String> second = factory.get();

        assertTrue(first.isEmpty());
        first.add("PEN-2"); // must not throw: the list has to be modifiable
        assertEquals(1, first.size());
        assertNotSame(first, second, "each call must create a new list");
        assertTrue(second.isEmpty());
    }
}
