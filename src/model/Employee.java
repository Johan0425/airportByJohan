/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.Role;

/**
 *
 * @author joanp
 */
public class Employee extends User {
    
    private String email;
    private double salary;

    public Employee(String email, double salary, Role role, String id, String fullname, String username, String password) {
        super(role, id, fullname, username, password);
        this.email = email;
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
}
