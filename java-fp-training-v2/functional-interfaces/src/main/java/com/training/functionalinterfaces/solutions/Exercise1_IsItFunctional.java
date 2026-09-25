package com.training.functionalinterfaces.solutions;

/**
 * Solution to Exercise 1 - Is it functional?
 *
 * <p>The only rule: count the ABSTRACT methods, including inherited ones, but
 * ignoring default methods, static methods, and redeclared public Object methods.
 * Exactly one means functional.
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

    public static boolean isPriceFormatterFunctional() {
        // One abstract method: format(double). The textbook case.
        return true;
    }

    public static boolean isOrderValidatorFunctional() {
        // Two abstract methods: validate(...) AND errorMessage(). A lambda can only
        // supply ONE body, so the compiler would not know which method it is for.
        return false;
    }

    public static boolean isTaxCalculatorFunctional() {
        // tax(double) is the only abstract method. totalWithTax has a body (default)
        // and zero() has a body (static), so they do not count.
        return true;
    }

    public static boolean isSkuComparatorFunctional() {
        // compare(...) is abstract. equals(Object) is also written without a body,
        // BUT it is a public method of java.lang.Object, so every implementation
        // already has it. The language spec excludes it from the count.
        // java.util.Comparator does exactly the same thing.
        return true;
    }

    public static boolean isAuditedFormatterFunctional() {
        // Pitfall: only looking at the child's body. It inherits format(double)
        // from PriceFormatter AND adds auditTag(): 1 + 1 = 2 abstract methods.
        return false;
    }

    public static boolean isEuroFormatterFunctional() {
        // Inherits format(double) (abstract) and adds only a default method.
        // Still exactly one abstract method.
        return true;
    }

    public static boolean isTaggedFunctional() {
        // ZERO abstract methods. The rule is EXACTLY one; with zero there is nothing
        // for a lambda to implement. (@FunctionalInterface here would give
        // "no abstract method found in interface Tagged".)
        return false;
    }

    public static TaxCalculator twentyFivePercentTax() {
        // The lambda IS the body of tax(double). totalWithTax(...) is inherited
        // from the default method and calls our lambda internally.
        return amount -> amount * 0.25;
    }
}
