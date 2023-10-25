/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author joanp
 */
public class AirplaneMaintenance implements Serializable {
    
    private final Airplane airplane;
    private final String details;
    private final LocalDate date;

    public AirplaneMaintenance(Airplane airplane, String details, LocalDate date) {
        this.airplane = airplane;
        this.details = details;
        this.date = date;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public String getDetails() {
        return details;
    }
    
    public LocalDate getDate() {
        return date;
    }

}
