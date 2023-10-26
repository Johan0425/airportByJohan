package controllers;

import enums.Role;
import java.util.ArrayList;
import model.Employee;
import model.Traveler;
import model.User;
import singleton.Singleton;
import util.LSE;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joanp
 */
public class MultiUserController {

    private final LSE<User> users;

    public MultiUserController() {
        this.users = Singleton.getINSTANCE().getUsers();
    }

    public boolean hasMultiUser(String id) {
        boolean isMultiUser = false;
        int numberList = 0;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole() == Role.TRAVELER && users.get(i).getId().equals(id)) {
                numberList++;
                break;
            }
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole() != Role.TRAVELER && users.get(i).getId().equals(id)) {
                numberList++;
                break;
            }
        }

        if (numberList == 2) {
            isMultiUser = true;
        }

        return isMultiUser;
    }

    public ArrayList<String> obtainRolesById(String id) {
        ArrayList<String> roles = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(id)) {
                switch (user.getRole()) {
                    case GENERAL_ADMIN:
                        roles.add("GENERAL_ADMIN");
                        break;
                    case AIRLINE_ADMIN:
                        roles.add("AIRLINE_ADMIN");
                        break;
                    case LOGISTICS_EMPLOYEE:
                        roles.add("LOGISTICS_EMPLOYEE");
                        break;
                    case TRAVELER:
                        roles.add("TRAVELER");
                        break;
                    case MAINTENANCE_MANAGER:
                        roles.add("MAINTENANCE_MANAGER");
                        break;
                    case FLIGHT_CAPTAIN:
                        roles.add("FLIGHT_CAPTAIN");
                        break;
                }
            }
        }

        return roles;
    }

}
