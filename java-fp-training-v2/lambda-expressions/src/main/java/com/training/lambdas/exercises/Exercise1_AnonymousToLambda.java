package com.training.lambdas.exercises;

import java.util.Comparator;
import java.util.List;

/**
 * Exercise 1 (easy): rewrite anonymous inner classes as lambdas.
 *
 * <p>Each method's Javadoc shows the OLD pre-Java 8 code. Return a lambda
 * that does exactly the same thing.
 *
 * <p>Run: {@code mvn test -pl lambda-expressions -Dtest=Exercise1_AnonymousToLambdaTest}
 */
public class Exercise1_AnonymousToLambda {

    /**
     * Shortest string first. The old way:
     * <pre>
     * return new Comparator&lt;String&gt;() {
     *     &#64;Override
     *     public int compare(String a, String b) {
     *         return Integer.compare(a.length(), b.length());
     *     }
     * };
     * </pre>
     * Example: sorting [banana, fig, apple] gives [fig, apple, banana].
     */
    public static Comparator<String> byLength() {
        // TODO: return a lambda equivalent of the anonymous class above.
        // Hint: keep only the parameter list and the expression after "return".
        //       The compiler already knows the types from Comparator<String>.
        throw new UnsupportedOperationException("TODO: Exercise 1 - byLength");
    }

    /**
     * Alphabetical order, but ignoring upper/lower case. The old way:
     * <pre>
     * return new Comparator&lt;String&gt;() {
     *     &#64;Override
     *     public int compare(String a, String b) {
     *         return a.compareToIgnoreCase(b);
     *     }
     * };
     * </pre>
     * Example: sorting [bob, Alice, carol] gives [Alice, bob, carol].
     */
    public static Comparator<String> ignoringCase() {
        // TODO: return a lambda.
        // Hint: same shape as byLength(), different expression.
        throw new UnsupportedOperationException("TODO: Exercise 1 - ignoringCase");
    }

    /**
     * A task that, when run, adds {@code message} to {@code log}. The old way:
     * <pre>
     * return new Runnable() {
     *     &#64;Override
     *     public void run() {
     *         log.add(message);
     *     }
     * };
     * </pre>
     * Nothing is added until someone calls {@code run()}.
     */
    public static Runnable addToLog(List<String> log, String message) {
        // TODO: return a lambda.
        // Hint: Runnable.run() takes no parameters, so the parameter list is empty.
        //       The lambda may use 'log' and 'message' because they never change.
        throw new UnsupportedOperationException("TODO: Exercise 1 - addToLog");
    }
}
