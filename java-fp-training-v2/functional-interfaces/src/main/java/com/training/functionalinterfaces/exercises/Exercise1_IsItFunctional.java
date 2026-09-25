package com.training.functionalinterfaces.exercises;

/**
 * Exercise 1 - Is it functional? (easy)
 *
 * <p>Below are seven small interfaces from an imaginary web shop. For each one,
 * decide whether it is a FUNCTIONAL interface (i.e. whether a lambda could
 * implement it) and make the matching method return {@code true} or {@code false}.
 *
 * <p>None of the interfaces has {@code @FunctionalInterface} on purpose - that
 * would give the answer away. Decide by counting, then check yourself: try
 * writing {@code PriceFormatter f = p -> "x";} for an interface and see if it compiles.
 *
 * <p>Finally, prove one of them is functional by returning a real lambda from
 * {@link #twentyFivePercentTax()}.
 *
 * <p>Example: an interface {@code interface Greeter { String greet(String name); }}
 * is functional, so {@code isGreeterFunctional()} would return {@code true}.
 */
public class Exercise1_IsItFunctional {

    /** A: formats a price for display. */
    public interface PriceFormatter {
        String format(double price);
    }

    /** B: validates an order id and explains why it is invalid. */
    public interface OrderValidator {
        boolean validate(String orderId);

        String errorMessage();
    }

    /** C: calculates tax, with a helper default method and a static factory. */
    public interface TaxCalculator {
        double tax(double amount);

        default double totalWithTax(double amount) {
            return amount + tax(amount);
        }

        static TaxCalculator zero() {
            return amount -> 0.0;
        }
    }

    /** D: compares two SKUs, and redeclares equals from Object (like java.util.Comparator does). */
    public interface SkuComparator {
        int compare(String a, String b);

        boolean equals(Object other);
    }

    /** E: extends A and adds an audit tag. */
    public interface AuditedFormatter extends PriceFormatter {
        String auditTag();
    }

    /** F: extends A and adds a convenience method. */
    public interface EuroFormatter extends PriceFormatter {
        default String formatEuro(double price) {
            return format(price) + " EUR";
        }
    }

    /** G: a marker-style interface with only a default method. */
    public interface Tagged {
        default String tag() {
            return "untagged";
        }
    }

    /**
     * @return whether {@link PriceFormatter} is a functional interface
     */
    public static boolean isPriceFormatterFunctional() {
        // TODO: return true or false
        // Hint: count the abstract methods. How many are there? How many should there be?
        throw new UnsupportedOperationException("TODO: Exercise 1 - isPriceFormatterFunctional");
    }

    /**
     * @return whether {@link OrderValidator} is a functional interface
     */
    public static boolean isOrderValidatorFunctional() {
        // TODO: return true or false
        // Hint: a method with no body inside an interface is abstract, even without the word "abstract".
        throw new UnsupportedOperationException("TODO: Exercise 1 - isOrderValidatorFunctional");
    }

    /**
     * @return whether {@link TaxCalculator} is a functional interface
     */
    public static boolean isTaxCalculatorFunctional() {
        // TODO: return true or false
        // Hint: methods that already have a body (default, static) are not "holes" for a lambda to fill.
        throw new UnsupportedOperationException("TODO: Exercise 1 - isTaxCalculatorFunctional");
    }

    /**
     * @return whether {@link SkuComparator} is a functional interface
     */
    public static boolean isSkuComparatorFunctional() {
        // TODO: return true or false
        // Hint: think about which class EVERY object already inherits an equals(Object) from.
        //       Then look at the Javadoc of java.util.Comparator.
        throw new UnsupportedOperationException("TODO: Exercise 1 - isSkuComparatorFunctional");
    }

    /**
     * @return whether {@link AuditedFormatter} is a functional interface
     */
    public static boolean isAuditedFormatterFunctional() {
        // TODO: return true or false
        // Hint: inherited abstract methods count too. Add up the parent's and the child's.
        throw new UnsupportedOperationException("TODO: Exercise 1 - isAuditedFormatterFunctional");
    }

    /**
     * @return whether {@link EuroFormatter} is a functional interface
     */
    public static boolean isEuroFormatterFunctional() {
        // TODO: return true or false
        // Hint: same counting as the previous one - but what kind of method did the child add?
        throw new UnsupportedOperationException("TODO: Exercise 1 - isEuroFormatterFunctional");
    }

    /**
     * @return whether {@link Tagged} is a functional interface
     */
    public static boolean isTaggedFunctional() {
        // TODO: return true or false
        // Hint: the rule is "exactly one". Is "at most one" the same thing?
        throw new UnsupportedOperationException("TODO: Exercise 1 - isTaggedFunctional");
    }

    /**
     * Returns a {@link TaxCalculator} for 25 % Norwegian VAT, written as a lambda.
     *
     * <p>Example: {@code twentyFivePercentTax().tax(100.0)} returns {@code 25.0},
     * and {@code twentyFivePercentTax().totalWithTax(100.0)} returns {@code 125.0}
     * (the default method comes for free).
     */
    public static TaxCalculator twentyFivePercentTax() {
        // TODO: return a lambda that implements the one abstract method of TaxCalculator
        // Hint: the lambda's parameter is the "amount"; its body computes only the tax, not the total.
        throw new UnsupportedOperationException("TODO: Exercise 1 - twentyFivePercentTax");
    }
}
