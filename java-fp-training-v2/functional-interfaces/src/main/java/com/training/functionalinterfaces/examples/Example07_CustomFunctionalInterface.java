package com.training.functionalinterfaces.examples;

/**
 * Example 07 - Writing your own functional interface.
 *
 * <p>There is no magic. A lambda is simply a short way of writing a class
 * that implements the ONE abstract method. Below we implement the same
 * interface three ways and they all behave identically.
 *
 * <p>When should you write your own instead of using java.util.function?
 * When a domain name makes the code clearer ({@code ShippingPolicy} tells you
 * more than {@code Function<Double, Double>}), when you need to declare a checked
 * exception, or when you need more than two parameters.
 */
public class Example07_CustomFunctionalInterface {

    /** Our own functional interface: three inputs, one result. No JDK type takes 3 inputs. */
    @FunctionalInterface
    interface ShippingPolicy {
        double cost(double weightKg, String country, boolean express);
    }

    /** Way 1: a normal named class. */
    static class StandardShipping implements ShippingPolicy {
        @Override
        public double cost(double weightKg, String country, boolean express) {
            double base = "NO".equals(country) ? 49 : 149;
            return base + weightKg * 10 + (express ? 100 : 0);
        }
    }

    public static void main(String[] args) {
        ShippingPolicy named = new StandardShipping();

        // Way 2: an anonymous class (how this was done before Java 8).
        ShippingPolicy anonymous = new ShippingPolicy() {
            @Override
            public double cost(double weightKg, String country, boolean express) {
                double base = "NO".equals(country) ? 49 : 149;
                return base + weightKg * 10 + (express ? 100 : 0);
            }
        };

        // Way 3: a lambda. Its parameter list and body are exactly the method's
        // parameters and body. The compiler knows WHICH method from the target type,
        // because there is only one abstract method to choose from.
        ShippingPolicy lambda = (weightKg, country, express) -> {
            double base = "NO".equals(country) ? 49 : 149;
            return base + weightKg * 10 + (express ? 100 : 0);
        };

        // All three are called the same way: through the interface method.
        System.out.println("Named class: " + named.cost(2, "NO", false));
        // prints Named class: 69.0
        System.out.println("Anonymous:   " + anonymous.cost(2, "NO", false));
        // prints Anonymous:   69.0
        System.out.println("Lambda:      " + lambda.cost(2, "NO", false));
        // prints Lambda:      69.0
        System.out.println("Lambda, SE, express: " + lambda.cost(2, "SE", true));
        // prints Lambda, SE, express: 269.0

        // The lambda object really is an instance of our interface.
        System.out.println("lambda instanceof ShippingPolicy? " + (lambda instanceof ShippingPolicy));
        // prints lambda instanceof ShippingPolicy? true
    }
}
