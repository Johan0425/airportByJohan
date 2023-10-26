/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import model.Airline;
import model.Employee;
import model.User;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class LoginController {

    private final LSE<User> users;
    private final LSE<Airline> airlines;

    public LoginController() {
        users = Singleton.getINSTANCE().getUsers();
        airlines = Singleton.getINSTANCE().getAirlines();
    }

    public User searchUser(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            User aux = users.get(i);
            if (aux.getUsername().equals(username) && aux.getPassword().equals(password)) {
                Singleton.getINSTANCE().setUser(aux);
                return aux;
            }
        }
        return null;
    }

    public void logout(String username) {
        for (int i = 0; i < users.size(); i++) {
            User aux = users.get(i);
            if (aux.getUsername().equals(username)) {
                Singleton.getINSTANCE().setUser(null);
            }
        }
    }

    public Airline searchAirline(String id) {
        for (int i = 0; i < airlines.size(); i++) {
            Airline airline = airlines.get(i);
            for (int j = 0; j < airline.getEmployees().size(); j++) {
                Employee employee = airline.getEmployees().get(j);
                if (employee.getId().equals(id)) {
                    return airlines.get(i);
                }
            }
        }
        return null;
    }

    public User getUser() {
        return Singleton.getINSTANCE().getUser();
    }

}
