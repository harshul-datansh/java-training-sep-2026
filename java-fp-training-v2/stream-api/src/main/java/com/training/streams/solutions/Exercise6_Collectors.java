package com.training.streams.solutions;

import com.training.streams.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Solution for Exercise 6 - Collectors. */
public class Exercise6_Collectors {

    public Map<Integer, String> idToName(List<Employee> employees) {
        // Ids are unique, so the 2-argument toMap is safe here.
        // If two employees ever shared an id, this would throw IllegalStateException ("Duplicate key ...").
        return employees.stream()
                .collect(Collectors.toMap(Employee::id, Employee::name));
    }

    public Map<String, Integer> highestSalaryByDepartment(List<Employee> employees) {
        // Department is NOT unique. The third argument is called whenever a key is already present:
        // it receives the existing value and the new value and returns the one to keep.
        // Integer::max is a BinaryOperator<Integer> (Topic 2), so the highest salary wins.
        return employees.stream()
                .collect(Collectors.toMap(Employee::department, Employee::salary, Integer::max));
    }

    public Map<String, List<String>> namesByDepartment(List<Employee> employees) {
        // groupingBy decides the key; the downstream collector decides what each group becomes.
        // mapping(Employee::name, toList()) = "for each group, take the names and put them in a list".
        // Encounter order is kept inside each list.
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name, Collectors.toList())));
    }

    public Map<String, Long> countByDepartment(List<Employee> employees) {
        // counting() returns Long (not Integer), which is why the map value type is Long.
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.counting()));
    }

    public String sortedNamesInBrackets(List<Employee> employees) {
        // Sort the names (Strings) after mapping, so we get alphabetical order.
        // joining(delimiter, prefix, suffix) adds the brackets once, around the whole result,
        // and still produces "[]" for an empty stream.
        return employees.stream()
                .map(Employee::name)
                .sorted()
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public String toCsvLine(Employee employee) {
        // joining only accepts CharSequence elements, so ints are converted with String.valueOf.
        // No trailing comma to strip off, unlike a StringBuilder loop.
        // (Real CSV also needs quoting of commas inside values; out of scope here.)
        return Stream.of(
                        String.valueOf(employee.id()),
                        employee.name(),
                        employee.department(),
                        String.valueOf(employee.salary()))
                .collect(Collectors.joining(","));
    }
}
