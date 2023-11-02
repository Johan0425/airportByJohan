/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.AirplaneStatus;
import enums.Cities;
import enums.Event;
import enums.Role;
import exceptions.FlightException;
import java.time.LocalDate;
import java.time.LocalTime;
import model.Airline;
import model.Airplane;
import model.Captain;
import model.Employee;
import model.Flight;
import singleton.Singleton;
import util.LSE;
import util.Stack;

/**
 *
 * @author joanp
 */
public class FlightsController {

    private final Airline airline;

    private Stack<Flight> Z;
    private Stack<Flight> Y;

    public FlightsController(Airline airline) {
        this.airline = airline;
        Z = new Stack<>();
        Y = new Stack<>();
    }

    public void addToZ(Flight flight) {
        Z.push(flight);
    }

    public void activateZ(Event evt) {
        Flight fli = Z.pop();
        applyZ(evt, fli);
        Y.push(fli);
    }

    public void activateY(Event evt) {
        Flight fli = Y.pop();
        applyY(evt, fli);
        Z.push(fli);
    }

    private void applyZ(Event evt, Flight flight) {
        switch (evt) {
            case ADD:
                deleteFlight(flight.getId());
                break;
            case UPDATE:
                break;
            case DELETE:
                addFlight(flight);
                break;
            default:
                break;
        }
    }

    private void applyY(Event evt, Flight flight) {
        switch (evt) {
            case ADD:
                addFlight(flight);
                break;
            case UPDATE:
                break;
            case DELETE:
                deleteFlight(flight.getId());
                break;
            default:
                break;
        }
    }

    public LSE<Flight> getFlights() {
        return airline.getFlights();
    }

    public Flight searchFlight(int id) {
        return airline.searchFlight(id);
    }

    public void addFlight(Flight flight) throws FlightException {

        if (isValidFlight(flight)) {
            airline.addFlight(flight);
        } else {
            throw new FlightException();
        }
    }

    public boolean updateFlight(Flight flight, int flightId) {
        if (isValidFlight(flight)) {
            return airline.updateFlight(flight, flightId);
        } else {
            throw new FlightException();
        }
    }

    public Flight deleteFlight(int id) {
        Flight flight = searchFlight(id);

        for (int i = 0; i < flight.getCaptain().getFlights().size(); i++) {
            Flight aux = flight.getCaptain().getFlights().get(i);
            if (aux.getId() == id) {
                flight.getCaptain().getFlights().remove(i);
                Singleton.getINSTANCE().writeUser();
            }
        }

        for (int i = 0; i < flight.getAirplane().getFlights().size(); i++) {
            Flight aux = flight.getAirplane().getFlights().get(i);
            if (aux.getId() == id) {
                flight.getAirplane().getFlights().remove(i);
            }
        }

        return airline.deleteFlight(id);
    }

    public Captain searchCaptain(String id) {
        for (int i = 0; i < airline.getEmployees().size(); i++) {
            if (airline.getEmployees().get(i).getId().equals(id)) {
                return (Captain) airline.getEmployees().get(i);
            }
        }
        return null;
    }

    public Airplane searchAirplane(String model) {
        for (int i = 0; i < airline.getAirplanes().size(); i++) {
            if (airline.getAirplanes().get(i).getModel().equals(model)) {
                return airline.getAirplanes().get(i);
            }
        }
        return null;
    }

    public LSE<Captain> listAvailableCaptains(Cities origin, LocalDate date, LocalTime hour, LocalTime endTime) {
        LSE<Captain> captains = new LSE<>();
        boolean isCaptainAvailable = false;
        for (int i = 0; i < alirlineCaptains().size(); i++) {
            Captain captain = alirlineCaptains().get(i);

            if (validCaptainSchedule(captain, date, hour, endTime)) {
                isCaptainAvailable = true;
            }

            if (isCaptainAvailable) {
                captains.addDato(captain);
            }
        }
        return captains;

    }

    public LSE<Airplane> listAvailableAirplanes(Cities origin, LocalDate date, LocalTime hour, LocalTime endTime) {
        LSE<Airplane> airplanes = new LSE();
        boolean isAirplaneAvailable = false;
        for (int i = 0; i < airlineAirplanes().size(); i++) {
            Airplane airplane = airlineAirplanes().get(i);
            if (airplane.getStatus() == AirplaneStatus.AVAILABLE) {

                if (validAirplaneSchedule(airplane, date, hour, endTime)) {
                    isAirplaneAvailable = true;
                }

                if (isAirplaneAvailable) {
                    airplanes.addDato(airplane);
                }
            }
        }
        return airplanes;
    }

    public Flight findNearestPreviousFlightForAirplane(Flight newFlight) {
        LSE<Flight> flights = newFlight.getAirplane().getFlights();
        Flight nearestPreviousFlight = null;

        for (int i = 0; i < flights.size(); i++) {

            Flight flight = flights.get(i);

            if (flight.getDate().isBefore(newFlight.getDate()) || (flight.getDate().equals(newFlight.getDate()) && flight.getHour().isBefore(newFlight.getHour()))) {
                if (nearestPreviousFlight == null || flight.getDate().isAfter(nearestPreviousFlight.getDate()) || flight.getDate().equals(nearestPreviousFlight.getDate())
                        && flight.getHour().isAfter(nearestPreviousFlight.getHour())) {
                    nearestPreviousFlight = flight;
                }
            }
        }
        return nearestPreviousFlight;
    }

    public Flight findNearestNextFlightForAirplane(Flight newFlight) {
        LSE<Flight> flights = newFlight.getAirplane().getFlights();
        Flight nearestNextFlight = null;

        for (int i = 0; i < flights.size(); i++) {

            Flight flight = flights.get(i);

            if (flight.getDate().isAfter(newFlight.getDate()) || (flight.getDate().equals(newFlight.getDate()) && flight.getHour().isAfter(newFlight.getHour()))) {
                if (nearestNextFlight == null || flight.getDate().isBefore(nearestNextFlight.getDate()) || flight.getDate().equals(nearestNextFlight.getDate())
                        && flight.getHour().isBefore(nearestNextFlight.getHour())) {
                    nearestNextFlight = flight;
                }
            }
        }
        return nearestNextFlight;
    }

    public Flight findNearestPreviousFlightForCaptain(Flight newFlight) {
        LSE<Flight> flights = newFlight.getCaptain().getFlights();
        Flight nearestPreviousFlight = null;

        for (int i = 0; i < flights.size(); i++) {

            Flight flight = flights.get(i);

            if (flight.getDate().isBefore(newFlight.getDate()) || (flight.getDate().equals(newFlight.getDate()) && flight.getHour().isBefore(newFlight.getHour()))) {
                if (nearestPreviousFlight == null || flight.getDate().isAfter(nearestPreviousFlight.getDate()) || flight.getDate().equals(nearestPreviousFlight.getDate())
                        && flight.getHour().isAfter(nearestPreviousFlight.getHour())) {
                    nearestPreviousFlight = flight;
                }
            }
        }
        return nearestPreviousFlight;
    }

    public Flight findNearestNextFlightForCaptain(Flight newFlight) {
        LSE<Flight> flights = newFlight.getCaptain().getFlights();
        Flight nearestNextFlight = null;

        for (int i = 0; i < flights.size(); i++) {

            Flight flight = flights.get(i);

            if (flight.getDate().isAfter(newFlight.getDate()) || (flight.getDate().equals(newFlight.getDate()) && flight.getHour().isAfter(newFlight.getHour()))) {
                if (nearestNextFlight == null || flight.getDate().isBefore(nearestNextFlight.getDate()) || flight.getDate().equals(nearestNextFlight.getDate())
                        && flight.getHour().isBefore(nearestNextFlight.getHour())) {
                    nearestNextFlight = flight;
                }
            }
        }
        return nearestNextFlight;
    }

    private boolean isValidFlight(Flight flight) {
        return isValidOriginAirplane(flight) && isValidOriginCaptain(flight)
                && isValidEndTimeForAirplane(flight) && isValidEndTimeForCaptain(flight)
                && isValidDestinationAirplane(flight) && isValidDestinationForCaptain(flight)
                && isValidStartTimeForAirplane(flight) && isValidStartTimeForCaptain(flight);
    }

    /**
     * Valida que el avión inicie el vuelo desde la ultima ubicación en donde
     * quedó.
     *
     * @param flight
     * @return
     */
    private boolean isValidOriginAirplane(Flight flight) {
        Flight nearestPreviousFlight = findNearestPreviousFlightForAirplane(flight);
        return nearestPreviousFlight == null || nearestPreviousFlight.getDestination() == flight.getOrigin();
    }

    /**
     * Valida que el avión tenga como destino, el origen del próximo vuelo.
     *
     * @param flight
     * @return
     */
    private boolean isValidDestinationAirplane(Flight flight) {
        Flight nearestPreviousFlight = findNearestNextFlightForAirplane(flight);
        return nearestPreviousFlight == null || nearestPreviousFlight.getOrigin() == flight.getDestination();
    }

    /**
     * Valida que el capitan inicie el vuelo desde la ultima ubicación en donde
     * quedó
     *
     * @param flight
     * @return
     */
    private boolean isValidOriginCaptain(Flight flight) {
        Flight nearestPreviousFlight = findNearestPreviousFlightForCaptain(flight);
        return nearestPreviousFlight == null || nearestPreviousFlight.getDestination() == flight.getOrigin();
    }

    /**
     * Valida que el capitan tenga como destino, el origen del proximo vuelo.
     *
     * @param flight
     * @return
     */
    private boolean isValidDestinationForCaptain(Flight flight) {
        Flight nearestNextFlight = findNearestNextFlightForCaptain(flight);
        return nearestNextFlight == null || nearestNextFlight.getOrigin() == flight.getDestination();
    }

    /**
     * Valida que la finalización del vuelo cumpla con llegar un hora antes del
     * vuelo siguiente en caso de ser el mismo dia,
     *
     * @param flight
     * @return
     */
    private boolean isValidEndTimeForAirplane(Flight flight) {
        Flight nearestNextFlight = findNearestNextFlightForAirplane(flight);

        if (nearestNextFlight == null) {
            return true;
        }

        LocalTime minimumEndTime = flight.getEndTime().plusHours(1);

        if (flight.getDate().equals(nearestNextFlight.getDate()) && nearestNextFlight.getHour().isBefore(minimumEndTime)) {
            return false;
        }

        return true;

    }

    /**
     * Valida que la finalización del vuelo cumpla con llegar una hora antes del
     * vuelo siguiente en caso de ser el mismo dia
     *
     * @param flight
     * @return
     */
    private boolean isValidEndTimeForCaptain(Flight flight) {
        Flight nearestNextFlight = findNearestNextFlightForCaptain(flight);

        if (nearestNextFlight == null) {
            return true;
        }

        LocalTime minimumEndTime = flight.getEndTime().plusHours(1);

        if (flight.getDate().equals(nearestNextFlight.getDate()) && nearestNextFlight.getHour().isBefore(minimumEndTime)) {
            return false;
        }

        return true;
    }

    /**
     * Valida que la hora de inicio del vuelo, en caso de ser el mismo dia, sea
     * 1 hora después de la hora de llegada del vuelo anterior
     *
     * @param flight
     * @return
     */
    private boolean isValidStartTimeForAirplane(Flight flight) {
        Flight nearestPreviousFlight = findNearestPreviousFlightForAirplane(flight);

        if (nearestPreviousFlight == null) {
            return true;
        }

        LocalTime minimumStartTime = nearestPreviousFlight.getEndTime().plusHours(1);

        if (flight.getDate().equals(nearestPreviousFlight.getDate()) && flight.getHour().isBefore(minimumStartTime)) {
            return false;
        }

        return true;
    }

    /**
     * Valida que la hora de inicio del vuelo, en caso de ser el mismo dia, sea
     * 1 hora después de la hora de llegada del vuelo anterior
     *
     * @param flight
     * @return
     */
    private boolean isValidStartTimeForCaptain(Flight flight) {
        Flight nearestPreviousFlight = findNearestPreviousFlightForCaptain(flight);

        if (nearestPreviousFlight == null) {
            return true;
        }

        LocalTime minimumStartTime = nearestPreviousFlight.getEndTime().plusHours(1);

        if (flight.getDate().equals(nearestPreviousFlight.getDate()) && flight.getHour().isBefore(minimumStartTime)) {
            return false;
        }

        return true;
    }

    private boolean validCaptainSchedule(Captain captain, LocalDate date, LocalTime hour, LocalTime endTime) {
        for (int i = 0; i < captain.getFlights().size(); i++) {
            Flight aux = captain.getFlights().get(i);
            if (date != null) {
                if (date.equals(aux.getDate()) && hour.isAfter(aux.getHour()) && hour.isBefore(aux.getEndTime())
                        || date.equals(aux.getDate()) && endTime.isAfter(aux.getHour()) && endTime.isBefore(aux.getEndTime())
                        || date.equals(aux.getDate()) && hour.equals(aux.getHour())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validAirplaneSchedule(Airplane airplane, LocalDate date, LocalTime hour, LocalTime endTime) {
        for (int i = 0; i < airplane.getFlights().size(); i++) {
            Flight aux = airplane.getFlights().get(i);
            if (date != null) {
                if (date.equals(aux.getDate()) && hour.isAfter(aux.getHour()) && hour.isBefore(aux.getEndTime())
                        || date.equals(aux.getDate()) && endTime.isAfter(aux.getHour()) && endTime.isBefore(aux.getEndTime())
                        || date.equals(aux.getDate()) && hour.equals(aux.getHour())) {
                    return false;
                }
            }
        }
        return true;
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
