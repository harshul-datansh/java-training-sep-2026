package com.training.lambdas;

import com.training.lambdas.exercises.Exercise3_BlockBodies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise3_BlockBodiesTest {

    @Test
    @DisplayName("maskCardNumber hides all but the last 4 digits")
    void masksLongNumbers() {
        Function<String, String> mask = Exercise3_BlockBodies.maskCardNumber();
        assertEquals("************5678", mask.apply("1234 5678 1234 5678"));
        assertEquals("*1234", mask.apply("91234"));
    }

    @Test
    @DisplayName("maskCardNumber leaves 4 or fewer digits unchanged (spaces removed)")
    void leavesShortNumbers() {
        Function<String, String> mask = Exercise3_BlockBodies.maskCardNumber();
        assertEquals("123", mask.apply("12 3"));
        assertEquals("1234", mask.apply("1234"));
    }

    @Test
    @DisplayName("byLengthThenAlphabetical breaks length ties alphabetically")
    void byLengthThenAlphabetical() {
        List<String> fruits = new ArrayList<>(List.of("pear", "fig", "apple", "kiwi"));
        fruits.sort(Exercise3_BlockBodies.byLengthThenAlphabetical());
        assertEquals(List.of("fig", "kiwi", "pear", "apple"), fruits);
    }

    @Test
    @DisplayName("httpStatusCategory covers every range and its edges")
    void httpStatusCategory() {
        Function<Integer, String> category = Exercise3_BlockBodies.httpStatusCategory();
        assertEquals("SUCCESS", category.apply(200));
        assertEquals("SUCCESS", category.apply(299));
        assertEquals("CLIENT_ERROR", category.apply(404));
        assertEquals("SERVER_ERROR", category.apply(503));
        assertEquals("OTHER", category.apply(302));
        assertEquals("OTHER", category.apply(600));
    }
}
