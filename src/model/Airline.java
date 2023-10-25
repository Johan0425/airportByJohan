/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import exceptions.UserAlreadyRegisteredException;
import java.io.Serializable;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class Airline implements Serializable {

    private final LSE<Employee> employees;
    private final LSE<Airplane> airplanes;
    private final LSE<Flight> flights;

    private Employee admin;
    private String name;

    public Airline(String name, Employee admin) {
        this.name = name;
        this.admin = admin;
        employees = new LSE<>();
        airplanes = new LSE<>();
        flights = new LSE<>();
    }

    public LSE<Employee> getEmployees() {
        return employees;
    }

    public LSE<Airplane> getAirplanes() {
        return airplanes;
    }

    public LSE<Flight> getFlightsHistory() {
        return flights;
    }

    public Employee getAdmin() {
        return admin;
    }

    public void setAdmin(Employee admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee searchEmployee(String id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                return employees.get(i);
            }
        }
        return null;
    }

    public void addEmployee(Employee employee) {
        Employee aux = searchEmployee(employee.getId());
        if (aux == null) {
            employees.addDato(employee);
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
        
        return wasEdited;
    }

    public Employee deleteEmployee(String id) {
        for (int i = 0; i < employees.size(); i++) {
            Employee aux = employees.get(i);
            if (aux.getId().equals(id)) {
                employees.remove(i);
                return aux;
            }
        }
        return null;
    }
    
    public Airplane searchAirplane(String model) {
        for (int i = 0; i < airplanes.size(); i++) {
            if (airplanes.get(i).getModel().equals(model)) {
                return airplanes.get(i);
            }
        }
        return null;
    }
    
    public void addAirplane(Airplane airplane) {
        airplanes.addDato(airplane);
    }
    
    public boolean updateAirplane(Airplane airplane) {
        Airplane aux = searchAirplane(airplane.getModel());
        boolean wasEdited = false;
        
        if (aux.getNumRows() != airplane.getNumRows()) {
            aux.setNumRows(airplane.getNumRows());
            wasEdited = true;
        }
        
        if (aux.getNumCols() != airplane.getNumCols()) {
            aux.setNumCols(airplane.getNumCols());
            wasEdited = true;
        }
        
        if (aux.getStatus() != airplane.getStatus()) {
            aux.setStatus(airplane.getStatus());
            wasEdited = true;
        }
        
        if (!aux.getModel().equals(airplane.getModel())) {
            aux.setModel(airplane.getModel());
            wasEdited = true;
        }
        
        Singleton.getINSTANCE().writeAirline();
        return wasEdited;
    }
    
    public Airplane deleteAirplane(String model) {
        for (int i = 0; i < airplanes.size(); i++) {
            Airplane aux = airplanes.get(i);
            if (aux.getModel().equals(model)) {
                airplanes.remove(i);
                Singleton.getINSTANCE().writeAirline();
                return aux;
            }
        }
        return null;
    }

}
