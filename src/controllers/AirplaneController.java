/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import model.Airline;
import model.Airplane;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class AirplaneController {

    private final Airline airline;

    public AirplaneController(Airline airline) {
        this.airline = airline;
    }

    public LSE<Airplane> getAirplanes() {
        return airline.getAirplanes();
    }

    public Airplane searchAirplane(String model) {
        return airline.searchAirplane(model);
    }
    
    public void addAirplane(Airplane airplane) {
        airline.addAirplane(airplane);
        Singleton.getINSTANCE().writeAirline();
    }
    
    public boolean updateAirplane(Airplane airplane) {
        return airline.updateAirplane(airplane);
    }
    
    public Airplane deleteAirplane(String model) {
        return airline.deleteAirplane(model);
    }
}
