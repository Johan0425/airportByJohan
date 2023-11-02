/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.Event;
import static enums.Event.ADD;
import static enums.Event.DELETE;
import static enums.Event.UPDATE;
import exceptions.AirlineAlreadyRegisteredException;
import exceptions.AirlineNameInUseException;
import model.Airline;
import model.Employee;
import singleton.Singleton;
import util.LSE;
import util.Stack;

/**
 *
 * @author joanp
 */
public class AirlineController extends BaseController {

    private final LSE<Airline> airlines;

    private Stack<Airline> Z;
    private Stack<Airline> Y;

    public AirlineController() {
        airlines = Singleton.getINSTANCE().getAirlines();
        Z = new Stack<>();
        Y = new Stack<>();
    }

    public void addToZ(Airline airline) {
        Z.push(airline);
    }

    public void activateZ(Event evt, Employee admin) {
        Airline air = Z.pop();
        applyZ(evt, air, admin);
        Y.push(air);
    }

    public void activateY(Event evt, Employee admin) {
        Airline air = Y.pop();
        applyY(evt, air, admin);
        Z.push(air);
    }

    private void applyZ(Event evt, Airline airline, Employee admin) {
        switch (evt) {
            case ADD:
                deleteAirline(airline.getName());
                break;
            case UPDATE:
                break;
            case DELETE:
                addAirline(airline, admin);
                break;
            default:
                break;
        }
    }

    private void applyY(Event evt, Airline airline, Employee admin) {
        switch (evt) {
            case ADD:
                addAirline(airline, admin);
                break;
            case UPDATE:
                break;
            case DELETE:
                deleteAirline(airline.getName());
                break;
            default:
                break;
        }
    }

    public LSE<Airline> getAirlines() {
        return airlines;
    }

    public Airline searchAirline(String name) {
        for (int i = 0; i < airlines.size(); i++) {
            if (airlines.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return airlines.get(i);
            }
        }
        return null;
    }

    public void addTravelerAsAdminAirline(Airline airline, Employee admin) {
        Airline aux = searchAirline(airline.getName());
        if (aux == null) {
            airlines.addDato(airline);
            users.addDato(admin);
            airline.getEmployees().addDato(admin);
            Singleton.getINSTANCE().writeAirline();
            Singleton.getINSTANCE().writeUser();
        }
    }

    public void addAirline(Airline airline, Employee admin) {
        Airline aux = searchAirline(airline.getName());
        if (aux == null) {
            airlines.addDato(airline);
            users.addDato(admin);
            airline.getEmployees().addDato(admin);
            Singleton.getINSTANCE().writeAirline();
            Singleton.getINSTANCE().writeUser();
        } else {
            throw new AirlineAlreadyRegisteredException(airline.getName());
        }
    }

    public boolean updateAirline(Airline airline, String previousName) {
        Airline aux = searchAirline(previousName);
        boolean wasEdited = false;

        if (!aux.getName().equals(airline.getName())) {
            validateName(airline.getName());
            aux.setName(airline.getName());
            wasEdited = true;
        }

        if (wasEdited) {
            Singleton.getINSTANCE().writeAirline();
            return true;
        } else {
            return false;
        }
    }

    public Airline deleteAirline(String name) {
        for (int i = 0; i < airlines.size(); i++) {
            if (airlines.get(i).getName().equals(name)) {
                Airline aux = airlines.get(i);

                for (int j = 0; j < aux.getEmployees().size(); j++) {
                    deleteEmployeeFromSingleton(aux.getEmployees().get(j).getId());
                }

                airlines.remove(i);

                Singleton.getINSTANCE().writeAirline();
                return aux;
            }
        }
        return null;
    }

    public void validateName(String name) {
        for (int i = 0; i < airlines.size(); i++) {
            if (airlines.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                throw new AirlineNameInUseException(name);
            }
        }
    }

    private void deleteEmployeeFromSingleton(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Employee employee) {
                if (employee.getId().equals(id)) {
                    users.remove(i);
                    Singleton.getINSTANCE().writeUser();
                }
            }
        }
    }
}
