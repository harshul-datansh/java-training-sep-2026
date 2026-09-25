package com.training.demo.lambdas.demo;

import com.training.demo.model.Order;
import com.training.demo.model.SampleData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * DEMO 5 - Method references: a shorter lambda when the lambda ONLY calls one existing method.
 *
 *   o -> Demo05_MethodReferences.isLargeOrder(o)   becomes   Demo05_MethodReferences::isLargeOrder
 *
 * There are exactly 4 kinds. Each line below shows the lambda AND the method reference.
 */
public class Demo05_MethodReferences {

    // A static helper we want to reuse.
    static boolean isLargeOrder(Order o) {
        return o.total() >= 500;
    }

    public static void main(String[] args) {
        List<Order> orders = SampleData.orders();
        Order first = orders.get(0);     // A-100, anna@shop.test
        Order laptop = orders.get(2);    // A-102, total 560.00

        // 1) STATIC method:  ClassName::staticMethod
        Predicate<Order> largeLambda = o -> Demo05_MethodReferences.isLargeOrder(o);
        Predicate<Order> largeRef = Demo05_MethodReferences::isLargeOrder;
        System.out.println("1) static             : A-102 large? " + largeRef.test(laptop)
                + "   (lambda: " + largeLambda.test(laptop) + ")");

        // 2) BOUND instance method (one PARTICULAR object):  object::method
        //    The object (here the String "anna@shop.test") is fixed when the reference is created.
        String vipEmail = "anna@shop.test";
        Predicate<String> isVipLambda = email -> vipEmail.equals(email);
        Predicate<String> isVipRef = vipEmail::equals;
        System.out.println("2) bound (particular) : anna is VIP? " + isVipRef.test(first.customerEmail())
                + "   (lambda: " + isVipLambda.test(first.customerEmail()) + ")");

        //    System.out::println is bound too: System.out is one particular PrintStream object.
        System.out.println("   System.out::println on the first two ids:");
        orders.stream().limit(2).map(Order::id).forEach(System.out::println);

        // 3) UNBOUND instance method (an ARBITRARY object of the type):  ClassName::instanceMethod
        //    The object is the lambda's FIRST PARAMETER - a different order on every call.
        Function<Order, String> emailLambda = o -> o.customerEmail();
        Function<Order, String> emailRef = Order::customerEmail;
        System.out.println("3) unbound (arbitrary): " + emailRef.apply(first)
                + "   (lambda: " + emailLambda.apply(first) + ")");

        //    With two parameters: the first is the object, the second is the argument.
        BiFunction<String, String, Boolean> startsWith = String::startsWith; // (s, prefix) -> s.startsWith(prefix)
        System.out.println("   String::startsWith with (\"A-100\", \"A-\") = " + startsWith.apply(first.id(), "A-"));

        // 4) CONSTRUCTOR:  ClassName::new
        Supplier<List<Order>> newListLambda = () -> new ArrayList<>();
        Supplier<List<Order>> newListRef = ArrayList::new;
        List<Order> basket = newListRef.get();
        basket.add(first);
        System.out.println("4) constructor        : new basket has " + basket.size() + " order(s)"
                + "   (lambda list size: " + newListLambda.get().size() + ")");

        //    Constructor with an argument: s -> new StringBuilder(s)
        Function<String, StringBuilder> builderFactory = StringBuilder::new;
        System.out.println("   StringBuilder::new with \"A-100\", reversed = " + builderFactory.apply("A-100").reverse());
    }
}
