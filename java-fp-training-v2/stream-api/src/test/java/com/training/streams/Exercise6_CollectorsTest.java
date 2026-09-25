package com.training.streams;

import com.training.streams.exercises.Exercise6_Collectors;
import com.training.streams.model.Employee;
import com.training.streams.model.SampleData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exercise 6 - Collectors")
class Exercise6_CollectorsTest {

    private final Exercise6_Collectors exercise = new Exercise6_Collectors();
    private final List<Employee> employees = SampleData.employees();

    @Test
    @DisplayName("idToName maps every id to its name")
    void idToName() {
        assertEquals(Map.of(1, "Cy", 2, "Ann", 3, "Bo", 4, "Dan", 5, "Eve", 6, "Fay"),
                exercise.idToName(employees));
    }

    @Test
    @DisplayName("highestSalaryByDepartment keeps the highest salary for repeated departments")
    void highestSalary() {
        // Engineering: 85000, 91000, 78000 -> 91000. Sales: 52000, 58000 -> 58000. Finance: 60000.
        assertEquals(Map.of("Engineering", 91000, "Sales", 58000, "Finance", 60000),
                exercise.highestSalaryByDepartment(employees));
    }

    @Test
    @DisplayName("highestSalaryByDepartment does not depend on the order of the input")
    void highestSalaryFirstIsHighest() {
        List<Employee> list = List.of(
                new Employee(1, "A", "Ops", 70000),
                new Employee(2, "B", "Ops", 50000),
                new Employee(3, "C", "Ops", 60000));
        assertEquals(Map.of("Ops", 70000), exercise.highestSalaryByDepartment(list));
    }

    @Test
    @DisplayName("namesByDepartment groups names, keeping input order inside each group")
    void namesByDepartment() {
        assertEquals(Map.of(
                        "Engineering", List.of("Cy", "Bo", "Fay"),
                        "Sales", List.of("Ann", "Eve"),
                        "Finance", List.of("Dan")),
                exercise.namesByDepartment(employees));
    }

    @Test
    @DisplayName("countByDepartment counts employees per department as Long values")
    void countByDepartment() {
        assertEquals(Map.of("Engineering", 3L, "Sales", 2L, "Finance", 1L),
                exercise.countByDepartment(employees));
    }

    @Test
    @DisplayName("sortedNamesInBrackets returns all names sorted, as [Ann, Bo, ...]")
    void sortedNamesInBrackets() {
        assertEquals("[Ann, Bo, Cy, Dan, Eve, Fay]", exercise.sortedNamesInBrackets(employees));
    }

    @Test
    @DisplayName("sortedNamesInBrackets of an empty list is []")
    void sortedNamesInBracketsEmpty() {
        assertEquals("[]", exercise.sortedNamesInBrackets(List.of()));
    }

    @Test
    @DisplayName("toCsvLine joins id, name, department and salary with commas")
    void toCsvLine() {
        assertEquals("1,Cy,Engineering,85000", exercise.toCsvLine(employees.get(0)));
        assertEquals("5,Eve,Sales,58000", exercise.toCsvLine(employees.get(4)));
    }
}
