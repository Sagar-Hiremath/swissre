package org.swissre.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final double salary;
    private final Integer managerId;

    private Employee manager;

    private final List<Employee> subordinates = new ArrayList<>();

    public Employee(int id, String firstName, String lastName, double salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public Employee getManager() {
        return manager;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void addSubordinate(Employee subordinate) {
        this.subordinates.add(subordinate);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}