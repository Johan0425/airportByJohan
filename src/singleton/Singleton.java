/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Airline;
import model.User;
import util.LSE;

/**
 *
 * @author joanp
 */
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private final LSE<User> users;
    private final LSE<Airline> airlines;

    private User user;

    private Singleton() {
        users = readUsers();
        airlines = readAirlines();
    }

    public static Singleton getINSTANCE() {
        return INSTANCE;
    }

    public LSE<User> getUsers() {
        return users;
    }

    public LSE<Airline> getAirlines() {
        return airlines;
    }

    public User getUser() {
        if (user != null) {
            return user;
        } else {
            throw new IllegalStateException("El usuario no está inicializado.");
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void writeUser() {
        try {
            FileOutputStream archivo = new FileOutputStream("users.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(users);
        } catch (IOException ex) {
        }
    }

    private LSE<User> readUsers() {
        try {
            FileInputStream archivo = new FileInputStream("users.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return (LSE<User>) lector.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return new LSE<>();
        }
    }

    public void writeAirline() {
        try {
            FileOutputStream archivo = new FileOutputStream("airlines.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(airlines);
        } catch (IOException ex) {
        }
    }

    private LSE<Airline> readAirlines() {
        try {
            FileInputStream archivo = new FileInputStream("airlines.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return (LSE<Airline>) lector.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return new LSE<>();
        }
    }

}
