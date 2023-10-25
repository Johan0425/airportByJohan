/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author joanp
 */
public class UsernameInUseException extends RuntimeException {
    public UsernameInUseException() {
        super("Username en uso");
    }
}
