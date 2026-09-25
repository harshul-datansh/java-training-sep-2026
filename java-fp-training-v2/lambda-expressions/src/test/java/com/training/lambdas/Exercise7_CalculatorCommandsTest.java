package com.training.lambdas;

import com.training.lambdas.exercises.Exercise7_CalculatorCommands;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Exercise7_CalculatorCommandsTest {

    @Test
    @DisplayName("operations contains exactly the six operators")
    void operationKeys() {
        assertEquals(Set.of("+", "-", "*", "/", "max", "min"),
                Exercise7_CalculatorCommands.operations().keySet());
    }

    @Test
    @DisplayName("each operation computes the right value")
    void operationValues() {
        Map<String, BiFunction<Integer, Integer, Integer>> ops = Exercise7_CalculatorCommands.operations();
        assertEquals(7, ops.get("+").apply(3, 4));
        assertEquals(-1, ops.get("-").apply(3, 4));
        assertEquals(12, ops.get("*").apply(3, 4));
        assertEquals(3, ops.get("/").apply(7, 2));
        assertEquals(4, ops.get("max").apply(3, 4));
        assertEquals(3, ops.get("min").apply(3, 4));
    }

    @Test
    @DisplayName("evaluate handles a single expression")
    void evaluate() {
        assertEquals(7, Exercise7_CalculatorCommands.evaluate("3 + 4"));
        assertEquals(10, Exercise7_CalculatorCommands.evaluate("10 max 2"));
        assertEquals(-6, Exercise7_CalculatorCommands.evaluate("-2 * 3"));
    }

    @Test
    @DisplayName("evaluate rejects unknown operators with a clear message")
    void evaluateUnknownOperator() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Exercise7_CalculatorCommands.evaluate("3 % 4"));
        assertEquals("Unknown operator: %", e.getMessage());
    }

    @Test
    @DisplayName("evaluateChain works strictly left to right")
    void evaluateChain() {
        assertEquals(20, Exercise7_CalculatorCommands.evaluateChain("2 + 3 * 4"));
        assertEquals(5, Exercise7_CalculatorCommands.evaluateChain("5"));
        assertEquals(1, Exercise7_CalculatorCommands.evaluateChain("10 / 3 min 1"));
    }

    @Test
    @DisplayName("evaluateChain rejects unknown operators too")
    void evaluateChainUnknownOperator() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Exercise7_CalculatorCommands.evaluateChain("1 + 2 ^ 3"));
        assertEquals("Unknown operator: ^", e.getMessage());
    }
}
