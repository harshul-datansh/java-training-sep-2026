package com.training.lambdas.examples;

import com.training.lambdas.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * Example 09: method reference kind 4 of 4, a CONSTRUCTOR.
 *
 * <pre>
 *   lambda:            name -&gt; new Customer(name)
 *   method reference:  Customer::new
 * </pre>
 *
 * Which constructor is used? The one whose parameters match the functional
 * interface. Same {@code Customer::new}, different constructors below.
 */
public class Example09_ConstructorReference {

    public static void main(String[] args) {
        // No arguments: Supplier.get() -> new ArrayList<>()
        Supplier<List<String>> newList = ArrayList::new;
        List<String> list = newList.get();
        list.add("first");
        System.out.println("new list: " + list); // prints new list: [first]

        // One argument: Function.apply(name) -> new Customer(name)
        Function<String, Customer> fromName = Customer::new;
        System.out.println(fromName.apply("Ada"));
        // prints Customer[name=Ada, email=ada@example.com]

        // Two arguments: BiFunction.apply(name, email) -> new Customer(name, email)
        BiFunction<String, String, Customer> fromNameAndEmail = Customer::new;
        System.out.println(fromNameAndEmail.apply("Bob", "bob@corp.no"));
        // prints Customer[name=Bob, email=bob@corp.no]

        // Arrays have their own form: size -> new String[size]
        IntFunction<String[]> newArray = String[]::new;
        System.out.println("array length: " + newArray.apply(3).length); // prints array length: 3

        // Where you'll meet this for real: turning data into objects, e.g.
        //   names.stream().map(Customer::new).toList()   (Topic 3)
    }
}
