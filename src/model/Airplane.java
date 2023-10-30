/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.AirplaneStatus;
import enums.Cities;
import java.io.Serializable;
import util.LSE;

/**
 *
 * @author joanp
 */
public class Airplane implements Serializable {

    private final LSE<Flight> flights;
    private final Airline airline;
    private int numRows;
    private int numCols;
    private String model;
    private AirplaneMaintenance maintenance;
    private AirplaneStatus status;
    private Cities location;

    public Airplane(Airline airline, int numRows, int numCols, String model) {
        this.airline = airline;
        this.numRows = numRows;
        this.numCols = numCols;
        this.model = model;
        location = Cities.AXM;
        flights = new LSE<>();
        status = AirplaneStatus.AVAILABLE;
    }

    public LSE<Flight> getFlights() {
        return flights;
    }

    public Airline getAirline() {
        return airline;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public AirplaneMaintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(AirplaneMaintenance maintenance) {
        this.maintenance = maintenance;
    }

    public AirplaneStatus getStatus() {
        return status;
    }

    public void setStatus(AirplaneStatus status) {
        this.status = status;
    }

    public Cities getLocation() {
        return location;
    }

    public void setLocation(Cities location) {
        this.location = location;
    }

}
