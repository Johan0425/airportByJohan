/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author joanp
 */
public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String id) {
        super ("El usuario con cédula " + id + " ya se encuentra registrado");
    }
}
