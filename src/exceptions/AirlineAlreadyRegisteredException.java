/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author joanp
 */
public class AirlineAlreadyRegisteredException extends RuntimeException {
    public AirlineAlreadyRegisteredException(String name) {
        super("La aerolinea " + name + " ya se encuentra registrada");
    }
}
