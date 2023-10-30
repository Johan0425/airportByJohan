/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.AirplaneStatus;
import enums.Cities;
import enums.Role;
import java.time.LocalDate;
import java.time.LocalTime;
import model.Airline;
import model.Airplane;
import model.Captain;
import model.Employee;
import model.Flight;
import util.LSE;

/**
 *
 * @author joanp
 */
public class FlightsController {

    private final Airline airline;

    public FlightsController(Airline airline) {
        this.airline = airline;
    }

    public LSE<Flight> getFlights() {
        return airline.getFlights();
    }

    public Flight searchFlight(int id) {
        return airline.searchFlight(id);
    }

    public void addFlight(Flight flight) {
        airline.addFlight(flight);
    }

    public boolean updateFlight(Flight flight) {
        return airline.updateFlight(flight);
    }

    public Flight deleteFlight(int id) {
        return airline.deleteFlight(id);
    }

    public LSE<Captain> listAvailableCaptains(Cities origin, LocalDate date, LocalTime hour, LocalTime endTime) {
        LSE<Captain> captains = new LSE<>();
        for (int i = 0; i < alirlineCaptains().size(); i++) {
            if (alirlineCaptains().get(i).getLocation() == origin) {
                boolean isCaptainAvailable = true;
                Captain captain = alirlineCaptains().get(i);
                for (int j = 0; j < captain.getFlights().size(); j++) {
                    Flight flight = captain.getFlights().get(j);
                    if (flight.getDate().isEqual(date) && !flight.getEndTime().isBefore(hour) && !flight.getHour().isAfter(endTime)) {
                        isCaptainAvailable = false;
                        break;
                    }
                }
                if (isCaptainAvailable) {
                    captains.addDato(captain);
                }
            }
        }
        return captains;
    }

    public LSE<Airplane> listAvailableAirplanes(Cities origin, LocalDate date, LocalTime hour, LocalTime endTime) {
        LSE<Airplane> airplanes = new LSE();
        for (int i = 0; i < airlineAirplanes().size(); i++) {
            Airplane airplane = airlineAirplanes().get(i);
            if (airplane.getStatus() == AirplaneStatus.AVAILABLE) {
                if (airplane.getLocation() == origin) {
                    boolean isAirplaneAvailable = true;
                    for (int j = 0; j < airplane.getFlights().size(); j++) {
                        Flight flight = airplane.getFlights().get(j);
                        if (flight.getDate().isEqual(date) && !flight.getEndTime().isBefore(hour) && !flight.getHour().isAfter(endTime)) {
                            isAirplaneAvailable = false;
                            break;
                        }
                    }
                    if (isAirplaneAvailable) {
                        airplanes.addDato(airplane);
                    }
                }
            }
        }
        return airplanes;

    }

    private LSE<Captain> alirlineCaptains() {
        LSE<Captain> captains = new LSE<>();

        for (int i = 0; i < airline.getEmployees().size(); i++) {
            Employee emp = airline.getEmployees().get(i);
            if (emp.getRole() == Role.FLIGHT_CAPTAIN) {
                captains.addDato((Captain) emp);
            }
        }
        return captains;
    }

    private LSE<Airplane> airlineAirplanes() {
        LSE<Airplane> airplanes = new LSE<>();

        for (int i = 0; i < airline.getAirplanes().size(); i++) {
            airplanes.addDato(airline.getAirplanes().get(i));
        }
        return airplanes;
    }

}
