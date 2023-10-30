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
import java.util.HashSet;
import java.util.Set;
import model.Airline;
import model.Seat;
import model.User;
import util.LSE;

/**
 *
 * @author joanp
 */
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private final Set<Integer> flightsIDs;
    private final LSE<User> users;
    private final LSE<Airline> airlines;
    private final Seat seats[][];

    private User user;

    private Singleton() {
        flightsIDs = readFlightsIDs();
        users = readUsers();
        airlines = readAirlines();
        seats = readSeats();
    }

    public static Singleton getINSTANCE() {
        return INSTANCE;
    }

    public Set<Integer> getFlightsIDs() {
        return flightsIDs;
    }

    public Seat[][] getSeats() {
        return seats;
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
            throw new IllegalStateException("Debe iniciar sesión primero. no hay un usuario logueado.");
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

    public final Seat[][] readSeats() {
        try {
            FileInputStream archivo = new FileInputStream("seats.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return (Seat[][]) lector.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    public boolean writeSeats() {
        try {
            FileOutputStream archivo = new FileOutputStream("seats.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(seats);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public void writeFlightID() {
        try {
            FileOutputStream file = new FileOutputStream("flightsIDs.dat");
            ObjectOutputStream writer = new ObjectOutputStream(file);
            writer.writeObject(flightsIDs);
        } catch (IOException ex) {
        }
    }

    private Set<Integer> readFlightsIDs() {
        try {
            FileInputStream file = new FileInputStream("flightsIDs.dat");
            ObjectInputStream reader = new ObjectInputStream(file);
            return (Set<Integer>) reader.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return new HashSet<>();
        }
    }

}
