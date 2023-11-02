/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author joanp
 */
public class FlightException extends RuntimeException {

    public FlightException() {
        super("No se pudo programar el vuelo");
    }
}
