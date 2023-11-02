/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author jacobobc
 */
public class CaptainScheduleException extends RuntimeException {
    public CaptainScheduleException() {
        super("El capitán tiene otro vuelo programando dentro de este horario");
    }
}
