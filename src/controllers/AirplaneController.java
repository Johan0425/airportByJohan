/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.Event;
import static enums.Event.ADD;
import static enums.Event.DELETE;
import static enums.Event.UPDATE;
import model.Airline;
import model.Airplane;
import singleton.Singleton;
import util.LSE;
import util.Stack;

/**
 *
 * @author joanp
 */
public class AirplaneController {

    private final Airline airline;

    private Stack<Airplane> Z;
    private Stack<Airplane> Y;

    public AirplaneController(Airline airline) {
        this.airline = airline;
        Z = new Stack<>();
        Y = new Stack<>();
    }

    public void addToZ(Airplane airplane) {
        Z.push(airplane);
    }

    public void activateZ(Event evt) {
        Airplane air = Z.pop();
        applyZ(evt, air);
        Y.push(air);
    }

    public void activateY(Event evt) {
        Airplane air = Y.pop();
        applyY(evt, air);
        Z.push(air);
    }

    private void applyZ(Event evt, Airplane airplane) {
        switch (evt) {
            case ADD:
                deleteAirplane(airplane.getId());
                break;
            case UPDATE:
                break;
            case DELETE:
                addAirplane(airplane);
                break;
            default:
                break;
        }
    }

    private void applyY(Event evt, Airplane airplane) {
        switch (evt) {
            case ADD:
                addAirplane(airplane);
                break;
            case UPDATE:
                break;
            case DELETE:
                deleteAirplane(airplane.getId());
                break;
            default:
                break;
        }
    }

    public LSE<Airplane> getAirplanes() {
        return airline.getAirplanes();
    }

    public Airplane searchAirplane(int id) {
        return airline.searchAirplane(id);
    }

    public void addAirplane(Airplane airplane) {
        airline.addAirplane(airplane);
        Singleton.getINSTANCE().writeAirline();
    }

    public boolean updateAirplane(Airplane airplane) {
        return airline.updateAirplane(airplane);
    }

    public Airplane deleteAirplane(int id) {
        return airline.deleteAirplane(id);
    }
}
