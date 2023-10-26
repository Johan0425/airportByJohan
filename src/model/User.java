/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.Role;
import java.io.Serializable;

/**
 *
 * @author joanp
 */
public class User implements Serializable {
    
    protected Role role;
    protected final String id;
    protected final String fullname;
    
    protected String username;
    protected String password;

    public User(Role role, String id, String fullname, String username, String password) {
        this.role = role;
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
