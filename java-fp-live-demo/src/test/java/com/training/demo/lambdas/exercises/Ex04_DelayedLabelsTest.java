package com.training.demo.lambdas.exercises;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex04_DelayedLabelsTest {

    @Test
    void labelsAreCorrectWhenRunLater() {
        List<Order> firstThree = SampleData.orders().subList(0, 3);

        List<Supplier<String>> jobs = Ex04_DelayedLabels.labelJobs(firstThree);

        // The method has returned; now the "background job" runs the suppliers.
        List<String> labels = jobs.stream().map(Supplier::get).toList();
        assertEquals(List.of("1/3 A-100", "2/3 A-101", "3/3 A-102"), labels);
    }

    @Test
    void oneJobPerOrder() {
        assertEquals(7, Ex04_DelayedLabels.labelJobs(SampleData.orders()).size());
        assertEquals("7/7 A-106", Ex04_DelayedLabels.labelJobs(SampleData.orders()).get(6).get());
    }
}
