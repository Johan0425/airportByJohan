/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import model.Airline;
import model.Flight;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class FlightReservationController {

    private final LSE<Airline> airlines;

    public FlightReservationController() {
        this.airlines = Singleton.getINSTANCE().getAirlines();
    }

    public LSE<Flight> listAllFlights() {
        LSE<Flight> flights = new LSE<>();

        for (int i = 0; i < airlines.size(); i++) {
            Airline airline = airlines.get(i);
            for (int j = 0; j < airline.getFlights().size(); j++) {
                flights.addDato(airline.getFlights().get(j));
            }
        }
        return flights;
    }

    public Flight findFlightById(int flightId) {
        for (int i = 0; i < airlines.size(); i++) {
            Airline airline = airlines.get(i);
            for (int j = 0; j < airline.getFlights().size(); j++) {
                Flight flight = airline.getFlights().get(j);
                if (flight.getId() == flightId) {
                    return flight;
                }
            }
        }
        return null;
    }

}
