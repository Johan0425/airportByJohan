/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import singleton.Singleton;

/**
 *
 * @author joanp
 */
public class Flight implements Serializable {

    private final int id;
    private final LocalTime endTime;
    private Airplane airplane;
    private Employee captain;
    private LocalDate date;
    private LocalTime hour;
    private int aproximateTime;
    private String origin;
    private String destination;

    public Flight(Employee captain, Airplane airplane, LocalDate date, LocalTime hour, int aproximateTime, String origin, String destination) {
        this.captain = captain;
        this.airplane = airplane;
        this.date = date;
        this.hour = hour;
        this.aproximateTime = aproximateTime;
        this.origin = origin;
        this.destination = destination;
        endTime = hour.plusHours(aproximateTime);
        id = generateId();
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getId() {
        return id;
    }

    public Employee getCaptain() {
        return captain;
    }

    public void setCaptain(Employee captain) {
        this.captain = captain;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
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

    private int generateId() {
        Set<Integer> ids = Singleton.getINSTANCE().getFlightsIDs();

        int id = 1;
        while (ids.contains(id)) {
            id++;
        }

        ids.add(id);
        Singleton.getINSTANCE().writeFlightID();

        return id;
    }
}
