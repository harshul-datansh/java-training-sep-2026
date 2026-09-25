package com.training.streams.exercises;

import com.training.streams.model.Employee;

import java.util.List;
import java.util.Map;

/**
 * Exercise 6 - Collectors: toMap, groupingBy, counting, joining.
 *
 * <p>{@code collect(...)} is the terminal operation that builds a result container.
 * The {@code Collectors} class has ready-made recipes for maps, groups and strings.
 *
 * <p>Examples with {@link com.training.streams.model.SampleData#employees()}:
 * <pre>
 *   1 Cy  Engineering 85000     4 Dan Finance     60000
 *   2 Ann Sales       52000     5 Eve Sales       58000
 *   3 Bo  Engineering 91000     6 Fay Engineering 78000
 *
 *   idToName(employees)                  -> {1=Cy, 2=Ann, 3=Bo, 4=Dan, 5=Eve, 6=Fay}
 *   highestSalaryByDepartment(employees) -> {Engineering=91000, Sales=58000, Finance=60000}
 *   namesByDepartment(employees)         -> {Engineering=[Cy, Bo, Fay], Sales=[Ann, Eve], Finance=[Dan]}
 *   countByDepartment(employees)         -> {Engineering=3, Sales=2, Finance=1}
 *   sortedNamesInBrackets(employees)     -> "[Ann, Bo, Cy, Dan, Eve, Fay]"
 *   toCsvLine(employee 1)                -> "1,Cy,Engineering,85000"
 * </pre>
 * (The order of keys inside a HashMap is not defined; the tests compare maps with equals.)
 */
public class Exercise6_Collectors {

    /**
     * Maps each employee id to the employee name. Ids are unique.
     */
    public Map<Integer, String> idToName(List<Employee> employees) {
        // TODO: build a map from the stream.
        // Hint: Collectors.toMap(keyMapper, valueMapper). Both are Functions from Employee to something.
        throw new UnsupportedOperationException("TODO: Exercise 6 - idToName");
    }

    /**
     * Maps each department to the highest salary in that department.
     */
    public Map<String, Integer> highestSalaryByDepartment(List<Employee> employees) {
        // TODO: department is the key, salary is the value, but keys repeat!
        // Hint: toMap with only two arguments throws IllegalStateException on a duplicate key.
        //       The 3-argument toMap takes a merge function (a BinaryOperator) that decides
        //       which of two values to keep.
        throw new UnsupportedOperationException("TODO: Exercise 6 - highestSalaryByDepartment");
    }

    /**
     * Groups employee names by department. Inside each group, names keep the input order.
     */
    public Map<String, List<String>> namesByDepartment(List<Employee> employees) {
        // TODO: group by department, but store names instead of whole Employee objects.
        // Hint: groupingBy(classifier) alone gives Map<String, List<Employee>>.
        //       groupingBy has a second argument, a "downstream" collector: look at Collectors.mapping.
        throw new UnsupportedOperationException("TODO: Exercise 6 - namesByDepartment");
    }

    /**
     * Counts employees per department.
     */
    public Map<String, Long> countByDepartment(List<Employee> employees) {
        // TODO: group by department and count each group.
        // Hint: Collectors.counting() is a downstream collector and produces a Long.
        throw new UnsupportedOperationException("TODO: Exercise 6 - countByDepartment");
    }

    /**
     * All names sorted alphabetically, as one string like {@code "[Ann, Bo, Cy]"}.
     *
     * @return "[]" for an empty list
     */
    public String sortedNamesInBrackets(List<Employee> employees) {
        // TODO: names, sorted, joined.
        // Hint: Collectors.joining has a 3-argument version: delimiter, prefix, suffix.
        throw new UnsupportedOperationException("TODO: Exercise 6 - sortedNamesInBrackets");
    }

    /**
     * One employee as a CSV line: id, name, department and salary separated by commas.
     *
     * @return for employee 1: "1,Cy,Engineering,85000"
     */
    public String toCsvLine(Employee employee) {
        // TODO: build a small stream of the four field values and join them.
        // Hint: Stream.of(...) works for any fixed set of values. joining needs Strings,
        //       so the numbers must become text first (String.valueOf).
        throw new UnsupportedOperationException("TODO: Exercise 6 - toCsvLine");
    }
}
