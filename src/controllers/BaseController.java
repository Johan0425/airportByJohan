/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.Role;
import exceptions.UsernameInUseException;
import model.Employee;
import model.Traveler;
import model.User;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class BaseController {

    protected final LSE<User> users;

    public BaseController() {
        users = Singleton.getINSTANCE().getUsers();
    }

    public void validateUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                throw new UsernameInUseException();
            }
        }
    }

    public Traveler searchTraveler(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole() == Role.TRAVELER) {
                Traveler aux = (Traveler) users.get(i);
                if (aux.getId().equals(id)) {
                    return aux;
                }
            }
        }
        return null;
    }

    public Employee searchEmployee(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Employee employee) {
                if (employee.getId().equals(id)) {
                    return employee;
                }
            }
        }
        return null;
    }

    public boolean isUsernameInUse(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
