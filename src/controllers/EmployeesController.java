/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.Event;
import static enums.Event.ADD;
import static enums.Event.DELETE;
import static enums.Event.UPDATE;
import exceptions.UserAlreadyRegisteredException;
import model.Employee;
import singleton.Singleton;
import util.LSE;
import util.Stack;

/**
 *
 * @author joanp
 */
public class EmployeesController extends BaseController {

    private Stack<Employee> Z;
    private Stack<Employee> Y;

    public EmployeesController() {
        Z = new Stack<>();
        Y = new Stack<>();
    }

    public void addToZ(Employee employee) {
        Z.push(employee);
    }

    public void activateZ(Event evt) {
        Employee emp = Z.pop();
        applyZ(evt, emp);
        Y.push(emp);
    }

    public void activateY(Event evt) {
        Employee emp = Y.pop();
        applyY(evt, emp);
        Z.push(emp);
    }

    public void applyZ(Event evt, Employee employee) {
        switch (evt) {
            case ADD:
                deleteEmployeeFromSingleton(employee.getId());
                break;
            case UPDATE:
                updateEmployee(employee);
                break;
            case DELETE:
                addEmployee(employee);
                break;
            default:
                break;
        }
    }

    public void applyY(Event evt, Employee employee) {
        switch (evt) {
            case ADD:
                addEmployee(employee);
                break;
            case UPDATE:
                updateEmployee(employee);
                break;
            case DELETE:
                deleteEmployeeFromSingleton(employee.getId());
                break;
            default:
                break;
        }
    }

    public LSE<Employee> getEmployees() {
        LSE<Employee> employees = new LSE<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Employee employee) {
                employees.addDato(employee);
            }
        }
        return employees;
    }

    public void addEmployeeAsTraveler(Employee employee) {
        Employee aux = searchEmployee(employee.getId());
        if (aux == null) {
            users.addDato(employee);
            Singleton.getINSTANCE().writeUser();
        }
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
    
    //recordar poner tambien para edad y numero de telefono

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

    public Employee deleteEmployeeFromSingleton(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Employee) {
                Employee aux = (Employee) users.get(i);
                if (aux.getId().equals(id)) {
                    users.remove(i);
                    Singleton.getINSTANCE().writeUser();
                    return aux;
                }
            }
        }
        return null;
    }

}
