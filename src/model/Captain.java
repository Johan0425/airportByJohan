/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.Cities;
import enums.Role;
import util.LSE;

/**
 *
 * @author joanp
 */
public class Captain extends Employee {

    private final LSE<Flight> flights;
    private Cities location;

    public Captain(String email, double salary, String id, String fullname, String username, String password) {
        super(email, salary, Role.FLIGHT_CAPTAIN, id, fullname, username, password);
        location = Cities.AXM;
        flights = new LSE<>();
    }

    public LSE<Flight> getFlights() {
        return flights;
    }

    public Cities getLocation() {
        return location;
    }

    public void setLocation(Cities location) {
        this.location = location;
    }

}
