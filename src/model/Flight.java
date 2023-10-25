/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author joanp
 */
public class Flight implements Serializable {
    
    private final Employee captain;
    private final Airplane airplane;
    
    private AirplaneMaintenance maintenance; 
    private LocalDate date;
    private LocalTime hour;
    private int aproximateTime; 
    private String origin;
    private String destination;

    public Flight(Employee captain, Airplane airplane, AirplaneMaintenance maintenance, LocalDate date, LocalTime hour, int aproximateTime, String origin, String destination) {
        this.captain = captain;
        this.airplane = airplane;
        this.maintenance = maintenance;
        this.date = date;
        this.hour = hour;
        this.aproximateTime = aproximateTime;
        this.origin = origin;
        this.destination = destination;
    }

    public Employee getCaptain() {
        return captain;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public AirplaneMaintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(AirplaneMaintenance maintenance) {
        this.maintenance = maintenance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }
    
    public int getAproximateTime() {
        return aproximateTime;
    }

    public void setAproximateTime(int aproximateTime) {
        this.aproximateTime = aproximateTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
}
