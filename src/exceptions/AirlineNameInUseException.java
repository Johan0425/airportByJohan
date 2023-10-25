/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author joanp
 */
public class AirlineNameInUseException extends RuntimeException {
    public AirlineNameInUseException(String name) {
        super ("El nombre " + name + " ya se encuentra en uso");
    }
}
