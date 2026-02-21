package org.swissre.util;

import org.swissre.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Employee> readEmployees(String filePath) {

        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                double salary = Double.parseDouble(parts[3]);

                Integer managerId = null;
                if (parts.length > 4 && !parts[4].isEmpty()) {
                    managerId = Integer.parseInt(parts[4]);
                }

                Employee employee =
                        new Employee(id, firstName, lastName, salary, managerId);

                employees.add(employee);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}