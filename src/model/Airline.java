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

    private String name;

    public Airline(String name) {
        this.name = name;
        employees = new LSE<>();
        airplanes = new LSE<>();
        flights = new LSE<>();
    }

    public LSE<Flight> getFlights() {
        return flights;
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

    public void addTravelerAsEmployee(Employee employee) {
        Employee aux = searchEmployee(employee.getId());
        if (aux == null) {
            employees.addDato(employee);
        }
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

    public Airplane searchAirplane(int id) {
        for (int i = 0; i < airplanes.size(); i++) {
            if (airplanes.get(i).getId() == id) {
                return airplanes.get(i);
            }
        }
        return null;
    }

    public void addAirplane(Airplane airplane) {
        airplanes.addDato(airplane);
    }

    public boolean updateAirplane(Airplane airplane) {
        Airplane aux = searchAirplane(airplane.getId());
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

    public Airplane deleteAirplane(int id) {
        for (int i = 0; i < airplanes.size(); i++) {
            Airplane aux = airplanes.get(i);
            if (aux.getId() == id) {
                airplanes.remove(i);
                Singleton.getINSTANCE().writeAirline();
                return aux;
            }
        }
        return null;
    }

    public Flight searchFlight(int id) {
        for (int i = 0; i < flights.size(); i++) {
            Flight aux = flights.get(i);
            if (aux.getId() == id) {
                return aux;
            }
        }
        return null;
    }

    public void addFlight(Flight flight) {
        flights.addDato(flight);
        flight.getCaptain().getFlights().addDato(flight);
        flight.getAirplane().getFlights().addDato(flight);
        Singleton.getINSTANCE().writeAirline();
        Singleton.getINSTANCE().writeUser();
    }

    public boolean updateFlight(Flight flight, int flightId) {
        Flight aux = searchFlight(flightId);
        boolean wasEdited = false;

        if (!aux.getCaptain().getId().equals(flight.getCaptain().getId())) {

            for (int i = 0; i < aux.getCaptain().getFlights().size(); i++) {
                if (aux.getCaptain().getFlights().get(i).getId() == flightId) {
                    aux.getCaptain().getFlights().remove(i);
                }
            }
            aux.setCaptain(flight.getCaptain());
            flight.getCaptain().getFlights().addDato(aux);
            wasEdited = true;

        }

        if (!aux.getAirplane().getModel().equals(flight.getAirplane().getModel())) {

            for (int i = 0; i < aux.getAirplane().getFlights().size(); i++) {
                if (aux.getAirplane().getFlights().get(i).getId() == flightId) {
                    aux.getAirplane().getFlights().remove(i);
                }
            }
            aux.setAirplane(flight.getAirplane());
            flight.getAirplane().getFlights().addDato(aux);
            wasEdited = true;
        }

        if (aux.getDate() != flight.getDate()) {
            aux.setDate(flight.getDate());
            wasEdited = true;
        }

        if (aux.getHour() != flight.getHour()) {
            aux.setHour(flight.getHour());
            wasEdited = true;
        }

        if (aux.getAproximateTime() != flight.getAproximateTime()) {
            aux.setAproximateTime(flight.getAproximateTime());
            wasEdited = true;
        }

        if (!aux.getOrigin().equals(flight.getOrigin())) {
            aux.setOrigin(flight.getOrigin());
            wasEdited = true;
        }

        if (!aux.getDestination().equals(flight.getDestination())) {
            aux.setDestination(flight.getDestination());
            wasEdited = true;
        }

        Singleton.getINSTANCE().writeUser();
        Singleton.getINSTANCE().writeAirline();

        return wasEdited;
    }

    public Flight deleteFlight(int id) {
        for (int i = 0; i < flights.size(); i++) {
            Flight aux = flights.get(i);
            if (aux.getId() == id) {
                flights.remove(i);
                Singleton.getINSTANCE().writeAirline();
                return aux;
            }
        }
        return null;
    }

}
