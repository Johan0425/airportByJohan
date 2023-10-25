/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import exceptions.UserAlreadyRegisteredException;
import model.Employee;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class EmployeesController extends BaseController {

    public LSE<Employee> getEmployees() {
        LSE<Employee> employees = new LSE<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Employee employee) {
                employees.addDato(employee);
            }
        }
        return employees;
    }
    
    public void addEmployee(Employee employee) {
        Employee aux = searchEmployee(employee.getId());
        if (aux == null) {
            users.addDato(employee);
            Singleton.getINSTANCE().writeUser();
        } else {
            throw new UserAlreadyRegisteredException(employee.getId());
        }
    }

    public boolean updateEmployee(Employee employee) {
        Employee aux = searchEmployee(employee.getId());
        boolean wasEdited = false;

        if (!aux.getEmail().equals(employee.getEmail())) {
            aux.setEmail(employee.getEmail());
            wasEdited = true;
        }

        if (aux.getSalary() != (employee.getSalary())) {
            aux.setSalary(employee.getSalary());
            wasEdited = true;
        }

        if (!aux.getUsername().equals(employee.getUsername())) {
            aux.setUsername(employee.getUsername());
            wasEdited = true;
        }

        if (!aux.getPassword().equals(employee.getPassword())) {
            aux.setPassword(employee.getPassword());
            wasEdited = true;
        }

        if (wasEdited) {
            Singleton.getINSTANCE().writeUser();
            return true;
        } else {
            return false;
        }
    }

    public void deleteEmployeeFromSingleton(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Employee employee) {
                if (employee.getId().equals(id)) {
                    users.remove(i);
                    Singleton.getINSTANCE().writeUser();
                }
            }
        }
    }

}
