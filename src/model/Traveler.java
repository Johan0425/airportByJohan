/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.Role;
import util.LSE;

/**
 *
 * @author joanp
 */
public class Traveler extends User {

    private final LSE<FlightReservation> reservations;
    private final LSE<Flight> travelHistory;
    private final int age;

    public Traveler(int age, String id, String fullname, String username, String password) {
        super(Role.TRAVELER, id, fullname, username, password);
        this.age = age;
        reservations = new LSE<>();
        travelHistory = new LSE<>();
    }

    public LSE<FlightReservation> getReservations() {
        return reservations;
    }

    public LSE<Flight> getTravelHistory() {
        return travelHistory;
    }

    public int getAge() {
        return age;
    }

    public void bookFlight(FlightReservation flightReservation) {
        reservations.addDato(flightReservation);
    }

    public void cancelReservation(int id) {

    }
}
