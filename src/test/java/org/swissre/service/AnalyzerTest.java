package org.swissre.service;

import org.junit.jupiter.api.Test;
import org.swissre.model.Employee;
import org.swissre.util.CSVReader;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyzerTest {

    private static final Employee ceo;

    static {
        String path = AnalyzerTest.class.getResource("/test-employees.csv").getPath();
        if (path.startsWith("/") && path.length() > 2 && path.charAt(2) == ':') {
            path = path.substring(1);
        }
        List<Employee> employees = CSVReader.readEmployees(path);
        ceo = EmployeeService.buildHierarchy(employees);
    }
    @Test
    void getManagersEarningLess_containsExpectedEmployees() {
        Map<Employee, Double> result = Analyzer.getManagersEarningLess(ceo);
        assertTrue(result.keySet().stream().anyMatch(e -> e.getFullName().equals("Mark Henry")));
        assertTrue(result.keySet().stream().anyMatch(e -> e.getFullName().equals("Great Khali")));
    }

    @Test
    void getManagersEarningMore_containsExpectedEmployees() {
        Map<Employee, Double> result = Analyzer.getManagersEarningMore(ceo);
        assertTrue(result.keySet().stream().anyMatch(e -> e.getFullName().equals("Tripple H")));
        assertTrue(result.keySet().stream().anyMatch(e -> e.getFullName().equals("Randy Ortan")));
    }

    @Test
    void getReportingLineTooLong_containsExpectedEmployees() {
        Map<Employee, Integer> result = Analyzer.getReportingLineTooLong(ceo);
        assertTrue(result.keySet().stream().anyMatch(e -> e.getFullName().equals("The Mizz")));
        assertTrue(result.keySet().stream().anyMatch(e -> e.getFullName().equals("The Rock")));
        assertTrue(result.keySet().stream().anyMatch(e -> e.getFullName().equals("Boogy Man")));
    }
}