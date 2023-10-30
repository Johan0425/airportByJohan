/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author joanp
 */
public class NumberAlreadyInUse extends RuntimeException {

    public NumberAlreadyInUse() {

        super("El numero de telefono ya está en uso");
    }

}
