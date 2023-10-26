/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import exceptions.AirlineAlreadyRegisteredException;
import exceptions.AirlineNameInUseException;
import model.Airline;
import model.Employee;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class AirlineController extends BaseController {

    private final LSE<Airline> airlines;

    public AirlineController() {
        airlines = Singleton.getINSTANCE().getAirlines();
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

    public void addTravelerAsAdminAirline(Airline airline) {
        Airline aux = searchAirline(airline.getName());
        if (aux == null) {
            airlines.addDato(airline);
            users.addDato(airline.getAdmin());
            airline.getEmployees().addDato(airline.getAdmin());
            Singleton.getINSTANCE().writeAirline();
            Singleton.getINSTANCE().writeUser();
        }
    }

    public void addAirline(Airline airline) {
        Airline aux = searchAirline(airline.getName());
        if (aux == null) {
            airlines.addDato(airline);
            users.addDato(airline.getAdmin());
            airline.getEmployees().addDato(airline.getAdmin());
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
