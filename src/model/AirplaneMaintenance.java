/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.AirplaneRequestStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import singleton.Singleton;

/**
 *
 * @author joanp
 */
public class AirplaneMaintenance implements Serializable {
    
    private final int id;

    private final Airplane airplane;
    private final String details;
    private final LocalDate date;
    private boolean statusChat;

    private AirplaneRequestStatus state;
    private Chat chat;

    public AirplaneMaintenance(Airplane airplane, String details, LocalDate date) {
        this.airplane = airplane;
        this.details = details;
        this.date = date;
        this.statusChat = false;
        this.chat = null;
        this.id = generateId();
        this.state = AirplaneRequestStatus.ON_STANDBY;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public boolean isStatusChat() {
        return statusChat;
    }

    public void setStatusChat(boolean statusChat) {
        this.statusChat = statusChat;
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

    public AirplaneRequestStatus getState() {
        return state;
    }

    public void setState(AirplaneRequestStatus state) {
        this.state = state;
    }
    
    private int generateId() {
        Set<Integer> ids = Singleton.getINSTANCE().getMaintenanceId();

        int id = 1;
        while (ids.contains(id)) {
            id++;
        }

        ids.add(id);
        Singleton.getINSTANCE().writeMaintenanceId();

        return id;
    }

}
