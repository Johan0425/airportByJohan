/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.Role;
import exceptions.UserAlreadyRegisteredException;
import model.Traveler;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class TravelerController extends BaseController {

    public LSE<Traveler> getTravelers() {
        LSE<Traveler> travelers = new LSE<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole() == Role.TRAVELER) {
                travelers.addDato((Traveler) users.get(i));
            }
        }
        return travelers;
    }
    
    public void addEmployeeAsTraveler(Traveler traveler) {
        Traveler aux = searchTraveler(traveler.getId());
        if (aux == null) {
            users.addDato(traveler);
            Singleton.getINSTANCE().writeUser();
        }
    }

    public void addTraveler(Traveler traveler) {
        Traveler aux = searchTraveler(traveler.getId());
        if (aux == null) {
            validateUsername(traveler.getUsername());
            users.addDato(traveler);
            Singleton.getINSTANCE().writeUser();
        } else {
            throw new UserAlreadyRegisteredException(traveler.getId());
        }
    }

    public boolean updateTraveler(Traveler traveler) {
        Traveler aux = searchTraveler(traveler.getId());
        boolean wasEdited = false;

        if (!aux.getUsername().equals(traveler.getUsername())) {
            validateUsername(traveler.getUsername());
            aux.setUsername(traveler.getUsername());
            wasEdited = true;
        }

        if (!aux.getPassword().equals(traveler.getPassword())) {
            aux.setPassword(traveler.getPassword());
            wasEdited = true;
        }
        
        if (wasEdited) {
            Singleton.getINSTANCE().writeUser();
            return true;
        } else {
            return false;
        }
    }

    public Traveler deleteTraveler(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole() == Role.TRAVELER) {
                Traveler aux = (Traveler) users.get(i);
                if (aux.getId().equals(id)) {
                    users.remove(i);
                    Singleton.getINSTANCE().writeUser();
                    return aux;
                }
            }
        }
        return null;
    }
}
