package com.training.functionalinterfaces;

import com.training.functionalinterfaces.exercises.Exercise4_FunctionComposition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Exercise 4 - Function composition")
class Exercise4_FunctionCompositionTest {

    @Test
    @DisplayName("slugBuilder turns a blog title into a URL slug")
    void slugBuilderBuildsSlug() {
        Function<String, String> slug = Exercise4_FunctionComposition.slugBuilder();

        assertEquals("hello-world-2024", slug.apply("  Hello, World 2024! "));
        assertEquals("java-17-is-here", slug.apply("Java   17 is HERE"));
    }

    @Test
    @DisplayName("slugBuilder hyphenates spaces BEFORE stripping invalid characters")
    void slugBuilderOrderMatters() {
        // If STRIP_INVALID ran before SPACES_TO_HYPHENS the spaces would vanish: "springboot".
        assertEquals("spring-boot", Exercise4_FunctionComposition.slugBuilder().apply("Spring Boot"));
    }

    @Test
    @DisplayName("vatThenRounded adds VAT first, then rounds")
    void vatThenRounded() {
        Function<Double, Double> f = Exercise4_FunctionComposition.vatThenRounded();

        assertEquals(13.0, f.apply(10.4), 1e-9);  // 10.4 * 1.25 = 13.0 -> 13
        assertEquals(125.0, f.apply(100.0), 1e-9);
        assertEquals(13.0, f.apply(10.0), 1e-9);  // 12.5 -> 13
    }

    @Test
    @DisplayName("roundedThenVat rounds first, then adds VAT")
    void roundedThenVat() {
        Function<Double, Double> f = Exercise4_FunctionComposition.roundedThenVat();

        assertEquals(12.5, f.apply(10.4), 1e-9);  // 10.4 -> 10 -> 12.5
        assertEquals(12.5, f.apply(10.0), 1e-9);
    }

    @Test
    @DisplayName("same two functions, different order, different result")
    void orderChangesTheResult() {
        assertNotEquals(
                Exercise4_FunctionComposition.vatThenRounded().apply(10.4),
                Exercise4_FunctionComposition.roundedThenVat().apply(10.4));
    }

    @Test
    @DisplayName("formatWithVat adds VAT and then formats")
    void formatWithVat() {
        Function<Double, String> f = Exercise4_FunctionComposition.formatWithVat();

        assertEquals("NOK 12.5", f.apply(10.0));
        assertEquals("NOK 125.0", f.apply(100.0));
    }
}
