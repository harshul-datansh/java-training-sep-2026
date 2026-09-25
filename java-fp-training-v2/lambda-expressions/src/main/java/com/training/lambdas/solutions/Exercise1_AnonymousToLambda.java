package com.training.lambdas.solutions;

import java.util.Comparator;
import java.util.List;

/** Solution to Exercise 1. */
public class Exercise1_AnonymousToLambda {

    public static Comparator<String> byLength() {
        // Everything except "(a, b)" and the returned expression was ceremony:
        // the interface name, the method name, the types and "return" are all
        // known from the target type Comparator<String>.
        return (a, b) -> Integer.compare(a.length(), b.length());
    }

    public static Comparator<String> ignoringCase() {
        // Same shape, different expression. (Exercise 5 shows this can be
        // shortened further to a method reference: String::compareToIgnoreCase.)
        return (a, b) -> a.compareToIgnoreCase(b);
    }

    public static Runnable addToLog(List<String> log, String message) {
        // run() takes no parameters, hence "()". The lambda captures 'log' and
        // 'message'. Both are parameters that are never reassigned, so they
        // are effectively final. Creating the Runnable does NOT run it: the
        // add happens only when someone calls run().
        return () -> log.add(message);
    }
}
