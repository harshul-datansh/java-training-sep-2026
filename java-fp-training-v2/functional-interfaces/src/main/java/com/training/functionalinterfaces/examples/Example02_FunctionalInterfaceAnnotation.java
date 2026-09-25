package com.training.functionalinterfaces.examples;

/**
 * Example 02 - What does {@code @FunctionalInterface} actually do?
 *
 * <p>The annotation is OPTIONAL. An interface with one abstract method is
 * functional whether or not you annotate it. The annotation does two things:
 * <ol>
 *   <li>Compiler guard: if somebody later adds a second abstract method,
 *       compilation fails AT THE INTERFACE, with a clear message.</li>
 *   <li>Documentation: it tells readers "this is meant to be used with lambdas,
 *       please keep it to one abstract method".</li>
 * </ol>
 */
public class Example02_FunctionalInterfaceAnnotation {

    // Annotated and valid: exactly one abstract method.
    @FunctionalInterface
    interface ShippingCost {
        double forWeight(double kg);
    }

    // Not annotated, but still functional. Lambdas work just the same.
    interface TaxRule {
        double taxFor(double amount);
    }

    /*
     * BROKEN VERSION 1 - with the annotation.
     * If you uncomment this, javac (JDK 21, --release 17) reports the error
     * at the interface itself:
     *
     *   error: Unexpected @FunctionalInterface annotation
     *   @FunctionalInterface
     *   ^
     *     PriceRule is not a functional interface
     *       multiple non-overriding abstract methods found in interface PriceRule
     *
     * @FunctionalInterface
     * interface PriceRule {
     *     double apply(double price);
     *     String describe();          // second abstract method -> not functional
     * }
     *
     * (With ZERO abstract methods the last line says instead:
     *   no abstract method found in interface PriceRule)
     */

    /*
     * BROKEN VERSION 2 - WITHOUT the annotation.
     * The interface itself compiles fine! The error only shows up later,
     * wherever someone tries to assign a lambda to it:
     *
     *   error: incompatible types: PriceRule is not a functional interface
     *       PriceRule r = price -> price * 0.9;
     *                     ^
     *     multiple non-overriding abstract methods found in interface PriceRule
     *
     * interface PriceRule {
     *     double apply(double price);
     *     String describe();
     * }
     * PriceRule r = price -> price * 0.9;   // <- error reported HERE
     *
     * That is why the annotation is worth adding: it moves the error to the
     * place that actually caused it.
     */

    public static void main(String[] args) {
        ShippingCost shipping = kg -> 49 + kg * 10;
        TaxRule vat = amount -> amount * 0.25;

        System.out.println("Shipping for 2 kg: " + shipping.forWeight(2));
        // prints Shipping for 2 kg: 69.0
        System.out.println("VAT on 200:        " + vat.taxFor(200));
        // prints VAT on 200:        50.0

        // You can check for the annotation at runtime (it has RUNTIME retention),
        // but you will almost never need to.
        System.out.println("ShippingCost annotated? "
                + ShippingCost.class.isAnnotationPresent(FunctionalInterface.class));
        // prints ShippingCost annotated? true
        System.out.println("TaxRule annotated?      "
                + TaxRule.class.isAnnotationPresent(FunctionalInterface.class));
        // prints TaxRule annotated?      false
    }
}
