package org.swissre;

import org.swissre.model.Employee;
import org.swissre.service.Analyzer;
import org.swissre.service.EmployeeService;
import org.swissre.util.CSVReader;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final String DEFAULT_PATH = "src/main/resources/employees.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the full path of the file without double colon, For an Ex > C:\\Users\\JohnCena\\Downloads\\emp.csv");
        String filePath = scanner.nextLine().trim();

        String pathToUse = filePath;
        if (pathToUse.isEmpty()) {
            pathToUse = DEFAULT_PATH;
            System.out.println("No path provided. Using default: " + DEFAULT_PATH);
        }

        List<Employee> employees = CSVReader.readEmployees(pathToUse);

        if (employees.isEmpty() && !filePath.isEmpty()) {
            System.out.println("Could not read from given path. Using default: " + DEFAULT_PATH);
            pathToUse = DEFAULT_PATH;
            employees = CSVReader.readEmployees(pathToUse);
        }

        if (employees.isEmpty()) {
            System.err.println("No employees read. Exiting.");
            return;
        }

        Employee ceo = EmployeeService.buildHierarchy(employees);

        Map<Employee, Double> earningLess = Analyzer.getManagersEarningLess(ceo);
        Map<Employee, Double> earningMore = Analyzer.getManagersEarningMore(ceo);
        Map<Employee, Integer> reportingTooLong = Analyzer.getReportingLineTooLong(ceo);

        System.out.println("\n---------- Salary Violations -------------");
        for (var e : earningLess.entrySet()) {
            System.out.println("\"" + e.getKey().getFullName() + "\" earns less than required by " + String.format("%.2f", e.getValue()));
        }
        for (var e : earningMore.entrySet()) {
            System.out.println("\"" + e.getKey().getFullName() + "\" earns more than allowed by " + String.format("%.2f", e.getValue()));
        }

        System.out.println("\n---------- Reporting Violations ------------");
        for (var e : reportingTooLong.entrySet()) {
            System.out.println("\"" + e.getKey().getFullName() + "\" reporting line too long by " + (e.getValue() - 4));
        }
    }
}