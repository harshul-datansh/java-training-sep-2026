package com.training.streams;

import com.training.streams.exercises.Exercise4_FlatMap;
import com.training.streams.model.Article;
import com.training.streams.model.Order;
import com.training.streams.model.OrderLine;
import com.training.streams.model.SampleData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exercise 4 - flatMap")
class Exercise4_FlatMapTest {

    private final Exercise4_FlatMap exercise = new Exercise4_FlatMap();

    @Test
    @DisplayName("allTagsSorted returns every tag once, alphabetically")
    void allTagsSorted() {
        assertEquals(List.of("advanced", "beginner", "java", "lambdas", "records", "streams"),
                exercise.allTagsSorted(SampleData.articles()));
    }

    @Test
    @DisplayName("allTagsSorted returns an empty list when no article has tags")
    void allTagsSortedNoTags() {
        List<Article> untagged = List.of(new Article("A", List.of()), new Article("B", List.of()));
        assertEquals(List.of(), exercise.allTagsSorted(untagged));
    }

    @Test
    @DisplayName("totalQuantity adds the quantities of all lines of all orders (2+1+3+1+1 = 8)")
    void totalQuantity() {
        assertEquals(8, exercise.totalQuantity(SampleData.orders()));
    }

    @Test
    @DisplayName("totalQuantity is 0 for no orders")
    void totalQuantityEmpty() {
        assertEquals(0, exercise.totalQuantity(List.of()));
    }

    @Test
    @DisplayName("skusForCustomer(alice) returns her SKUs from both orders, first-seen order, no duplicates")
    void skusForAlice() {
        assertEquals(List.of("KB", "MS", "MN"), exercise.skusForCustomer(SampleData.orders(), "alice"));
    }

    @Test
    @DisplayName("skusForCustomer(bob) returns only bob's SKU")
    void skusForBob() {
        assertEquals(List.of("MS"), exercise.skusForCustomer(SampleData.orders(), "bob"));
    }

    @Test
    @DisplayName("skusForCustomer for a customer whose order has no lines is empty")
    void skusForCarol() {
        assertEquals(List.of(), exercise.skusForCustomer(SampleData.orders(), "carol"));
    }

    @Test
    @DisplayName("skusForCustomer for an unknown customer is empty")
    void skusForUnknown() {
        assertEquals(List.of(), exercise.skusForCustomer(SampleData.orders(), "dave"));
    }

    @Test
    @DisplayName("skusForCustomer removes a SKU repeated inside the same order")
    void skusRepeatedInOneOrder() {
        List<Order> orders = List.of(new Order("x", "zoe",
                List.of(new OrderLine("B", 1), new OrderLine("A", 1), new OrderLine("B", 4))));
        assertEquals(List.of("B", "A"), exercise.skusForCustomer(orders, "zoe"));
    }
}
