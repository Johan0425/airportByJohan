/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

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
        deleteEmployeeFromSingleton(id);
        Singleton.getINSTANCE().writeAirline();
        return airline.deleteEmployee(id);
    }

}
