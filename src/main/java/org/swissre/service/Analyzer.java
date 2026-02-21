package org.swissre.service;

import org.swissre.model.Employee;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Analyzer {

    public static Map<Employee, Double> getManagersEarningLess(Employee ceo) {
        Map<Employee, Double> result = new HashMap<>();
        Queue<Employee> queue = new ArrayDeque<>();
        queue.add(ceo);

        while (!queue.isEmpty()) {
            Employee manager = queue.poll();
            var subs = manager.getSubordinates();
            if (!subs.isEmpty()) {
                double avg = calculateAverage(subs);
                double min = avg * 1.20;
                if (manager.getSalary() < min) {
                    result.put(manager, min - manager.getSalary());
                }
                queue.addAll(subs);
            }
        }
        return result;
    }

    public static Map<Employee, Double> getManagersEarningMore(Employee ceo) {
        Map<Employee, Double> result = new HashMap<>();
        Queue<Employee> queue = new ArrayDeque<>();
        queue.add(ceo);

        while (!queue.isEmpty()) {
            Employee manager = queue.poll();
            var subs = manager.getSubordinates();
            if (!subs.isEmpty()) {
                double avg = calculateAverage(subs);
                double max = avg * 1.50;
                if (manager.getSalary() > max) {
                    result.put(manager, manager.getSalary() - max);
                }
                queue.addAll(subs);
            }
        }
        return result;
    }

    public static Map<Employee, Integer> getReportingLineTooLong(Employee ceo) {
        Map<Employee, Integer> result = new HashMap<>();
        Queue<Employee> queue = new ArrayDeque<>();
        Queue<Integer> depths = new ArrayDeque<>();
        queue.add(ceo);
        depths.add(0);

        while (!queue.isEmpty()) {
            Employee emp = queue.poll();
            int depth = depths.poll();
            if (depth > 4) {
                result.put(emp, depth);
            }
            for (Employee sub : emp.getSubordinates()) {
                queue.add(sub);
                depths.add(depth + 1);
            }
        }
        return result;
    }

    private static double calculateAverage(java.util.List<Employee> list) {
        double sum = 0;
        for (Employee e : list) {
            sum += e.getSalary();
        }
        return sum / list.size();
    }
}