package com.training.demo.lambdas.solutions;

import com.training.demo.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/** SOLUTION to exercise 4. */
public class Ex04_DelayedLabels {

    /**
     * Why: i changes every round (i++), so it cannot be captured. We copy what we
     * need into NEW locals (position, id) inside the loop body; those are created
     * fresh in each round and never change, so they are effectively final.
     */
    public static List<Supplier<String>> labelJobs(List<Order> orders) {
        List<Supplier<String>> jobs = new ArrayList<>();
        int count = orders.size();
        for (int i = 0; i < count; i++) {
            int position = i + 1;
            String id = orders.get(i).id();
            jobs.add(() -> position + "/" + count + " " + id);
        }
        return jobs;
    }
}
