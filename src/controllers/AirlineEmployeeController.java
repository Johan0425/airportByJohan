/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.Event;
import static enums.Event.ADD;
import static enums.Event.DELETE;
import static enums.Event.UPDATE;
import model.Airline;
import model.Employee;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class AirlineEmployeeController extends EmployeesController {

    private final Airline airline;

    public AirlineEmployeeController(Airline airline) {
        this.airline = airline;
    }

    @Override
    public void applyZ(Event evt, Employee employee) {
        switch (evt) {
            case ADD:
                deleteEmployee(employee.getId());
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

    @Override
    public void applyY(Event evt, Employee employee) {
        switch (evt) {
            case ADD:
                addEmployee(employee);
                break;
            case UPDATE:
                updateEmployee(employee);
                break;
            case DELETE:
                deleteEmployee(employee.getId());
                break;
            default:
                break;
        }
    }

    @Override
    public LSE<Employee> getEmployees() {
        return airline.getEmployees();
    }

    public void addTravelerAsEmployee(Employee employee) {
        airline.addTravelerAsEmployee(employee);
        users.addDato(employee);
        Singleton.getINSTANCE().writeAirline();
        Singleton.getINSTANCE().writeUser();

    }

    @Override
    public void addEmployee(Employee employee) {
        airline.addEmployee(employee);
        users.addDato(employee);
        Singleton.getINSTANCE().writeAirline();
        Singleton.getINSTANCE().writeUser();
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Employee aux = searchEmployee(employee.getId());

        if (airline.updateEmployee(employee)) {
            aux.setEmail(employee.getEmail());
            aux.setSalary(employee.getSalary());
            aux.setUsername(employee.getUsername());
            aux.setPassword(employee.getPassword());
            Singleton.getINSTANCE().writeUser();
            Singleton.getINSTANCE().writeAirline();
            return true;
        }
        return false;
    }

    public Employee deleteEmployee(String id) {
        airline.deleteEmployee(id);
        deleteEmployeeFromSingleton(id);
        Singleton.getINSTANCE().writeAirline();
        return airline.deleteEmployee(id);
    }

}
