package com.example.lab2;

public class Employee {
    private String name;
    private double salary;

    private double totalSalary;

    public Employee(String name, double salary, double totalSalary) {
        this.name = name;
        this.salary = salary;
        this.totalSalary = totalSalary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return name + " - " + salary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public double getTotalSalary() {
        return totalSalary;
    }

}