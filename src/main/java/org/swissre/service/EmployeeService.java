package org.swissre.service;

import org.swissre.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {

    public static Employee buildHierarchy(List<Employee> employees) {
        Map<Integer, Employee> map = new HashMap<>();

        // store all employees in map
        for (Employee emp : employees) {
            map.put(emp.getId(), emp);
        }
        Employee ceo = null;

        // connect manager and subordinates
        for (Employee emp : employees) {
            Integer managerId = emp.getManagerId();
            if (managerId == null) {
                ceo = emp;
            } else {
                Employee manager = map.get(managerId);
                emp.setManager(manager);
                manager.addSubordinate(emp);
            }
        }
        return ceo;
    }
}