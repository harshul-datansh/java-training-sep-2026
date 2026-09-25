package com.training.demo.lambdas.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.util.ArrayList;
import java.util.List;

/**
 * DEMO 4 - The loop-variable trap.
 *
 * We want a list of "jobs" (Runnables) that will run LATER, one per order,
 * each printing its position in the queue.
 */
public class Demo04_LoopVariableTrap {

    public static void main(String[] args) {
        List<Order> orders = SampleData.orders().subList(0, 3);
        List<Runnable> jobs = new ArrayList<>();

        // ---------------- TRAP (does not compile) ----------------
        // for (int i = 0; i < orders.size(); i++) {
        //     jobs.add(() -> System.out.println("job " + i + " -> " + orders.get(i).id()));
        // }
        // error: local variables referenced from a lambda expression must be final or effectively final
        //
        // Why: there is ONE variable i, and i++ changes it every round.
        // The jobs run AFTER the loop, when i == 3. If Java allowed this,
        // the jobs could not agree on which i they mean. So the compiler refuses.

        // ---------------- FIX 1: copy into a fresh local each round ----------------
        for (int i = 0; i < orders.size(); i++) {
            int position = i;               // a NEW variable per iteration, never changed
            Order order = orders.get(i);    // same idea
            jobs.add(() -> System.out.println("  job " + position + " -> " + order.id()));
        }

        // ---------------- FIX 2: enhanced for loop ----------------
        // The for-each variable is a fresh variable in every round, so it is effectively final.
        for (Order order : orders) {
            jobs.add(() -> System.out.println("  for-each job -> " + order.id()));
        }

        System.out.println("=== Running the jobs AFTER both loops have finished ===");
        jobs.forEach(Runnable::run);
    }
}
