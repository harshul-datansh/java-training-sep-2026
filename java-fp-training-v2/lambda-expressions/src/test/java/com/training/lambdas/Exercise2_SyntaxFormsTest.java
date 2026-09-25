package com.training.lambdas;

import com.training.lambdas.exercises.Exercise2_SyntaxForms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise2_SyntaxFormsTest {

    @Test
    @DisplayName("defaultUsername returns guest")
    void defaultUsername() {
        assertEquals("guest", Exercise2_SyntaxForms.defaultUsername().get());
    }

    @Test
    @DisplayName("shout upper-cases and adds !")
    void shout() {
        assertEquals("HI!", Exercise2_SyntaxForms.shout().apply("hi"));
        assertEquals("KAFKA ROCKS!", Exercise2_SyntaxForms.shout().apply("kafka rocks"));
    }

    @Test
    @DisplayName("repeat repeats the text n times")
    void repeat() {
        assertEquals("ababab", Exercise2_SyntaxForms.repeat().apply("ab", 3));
        assertEquals("", Exercise2_SyntaxForms.repeat().apply("ab", 0));
    }
}
