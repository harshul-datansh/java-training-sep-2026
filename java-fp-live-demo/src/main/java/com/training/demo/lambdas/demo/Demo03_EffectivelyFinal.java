package com.training.demo.lambdas.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.util.List;
import java.util.function.Predicate;

/**
 * DEMO 3 - Capturing variables: "effectively final".
 *
 * A lambda may USE local variables from the method around it,
 * but only if those variables never change after they get their value.
 * "Effectively final" = you could add the word final and it would still compile.
 *
 * WHY? The lambda can run LATER - after the method has returned, or on
 * another thread. By then the method's local variables are gone (they lived
 * on that method's stack frame). So Java COPIES the value into the lambda.
 * If you were allowed to change the original afterwards, the copy and the
 * original would silently disagree. Java forbids the change instead.
 */
public class Demo03_EffectivelyFinal {

    // The predicate is created here, but USED after this method has returned.
    static Predicate<Order> totalAbove(double threshold) {
        // threshold is never reassigned -> effectively final -> OK to capture.
        // Java copies the value of threshold into the lambda object.
        return o -> o.total() > threshold;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Order> orders = SampleData.orders();

        double th = 100; // effectively final
        Predicate<Order> bigOrder = totalAbove(th);   // totalAbove() has finished here...
        System.out.println("=== Lambda used after its method returned (threshold = 200) ===");
        for (Order o : orders) {
            // ...but the lambda still knows threshold = 200.0, because it has its own copy.
            System.out.println("  " + o.id() + "  total " + o.total() + "  -> " + bigOrder.test(o));
        }
        System.out.println();

        // The same idea on a completely different thread:
        String currency = "EUR"; // effectively final
        Thread worker = new Thread(
                () -> System.out.println("  [" + Thread.currentThread().getName() + "] reporting in " + currency),
                "report-thread");
        System.out.println("=== Lambda running on another thread ===");
        worker.start();
        worker.join();
        System.out.println();

        // ------------------------------------------------------------------
        // This would NOT compile (uncomment it live to show the error):
        //
        //   int count = 0;
        //   orders.forEach(o -> count++);
        //
        // javac says:
        //   error: local variables referenced from a lambda expression must be final or effectively final
        //
        // Fix: don't mutate from inside the lambda - let the library do the counting.
        // (Streams, topic 3, make this a one-liner.)
        // ------------------------------------------------------------------
        long paidCount = orders.stream().filter(Order::isPaid).count();
        System.out.println("=== Counting without mutating a captured variable ===");
        System.out.println("  paid orders: " + paidCount);
    }
}
