/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import model.Airplane;
import model.Seat;
import singleton.Singleton;

/**
 *
 * @author joanp
 */
public class PreviewAirplaneController {

    private final Seat seats[][];

    public PreviewAirplaneController(Airplane airplane) {
        seats = new Seat[airplane.getNumRows()][airplane.getNumCols()];
        initSeats();
    }

    private void initSeats() {
        if (seats[0][0] == null) {
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    seats[i][j] = new Seat();
                }
            }
            Singleton.getINSTANCE().writeSeats();
        }

        Singleton.getINSTANCE().readSeats();
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public Seat obtainSeats(int row, int column) {
        return seats[row][column];

    }

}
