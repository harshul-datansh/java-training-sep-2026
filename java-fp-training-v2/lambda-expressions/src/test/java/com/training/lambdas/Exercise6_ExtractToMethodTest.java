package com.training.lambdas;

import com.training.lambdas.exercises.Exercise6_ExtractToMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Exercise6_ExtractToMethodTest {

    @ParameterizedTest(name = "\"{0}\" is strong")
    @ValueSource(strings = {"Secret123", "Kafka2024!", "ABCDEFG1"})
    void strongPasswords(String password) {
        assertTrue(Exercise6_ExtractToMethod.isStrongPassword(password));
    }

    @ParameterizedTest(name = "\"{0}\" is weak")
    @NullSource
    @ValueSource(strings = {"secret123", "SecretABC", "Secret 123", "Sh0rt", ""})
    void weakPasswords(String password) {
        assertFalse(Exercise6_ExtractToMethod.isStrongPassword(password));
    }

    @Test
    @DisplayName("strongPasswordRule agrees with isStrongPassword")
    void ruleMatchesMethod() {
        Predicate<String> rule = Exercise6_ExtractToMethod.strongPasswordRule();
        assertTrue(rule.test("Secret123"));
        assertFalse(rule.test("secret123"));
    }

    @Test
    @DisplayName("weakPasswords keeps only the weak ones, in order")
    void weakPasswordsFiltersInOrder() {
        List<String> input = List.of("Secret123", "password", "Tr0ub4dor", "Sh0rt", "Secret 123");
        assertEquals(List.of("password", "Sh0rt", "Secret 123"),
                Exercise6_ExtractToMethod.weakPasswords(input));
    }
}
