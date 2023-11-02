/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import model.AirplaneMaintenance;
import singleton.Singleton;
import util.LSE;

/**
 *
 * @author joanp
 */
public class ChatController {

    private final LSE<String> messages;

    public ChatController(AirplaneMaintenance maintenance) {
        this.messages = maintenance.getChat().getMessages();
    }

    public void addMessages(String message) {
        if (message != null) {
            messages.addDato(message);
            Singleton.getINSTANCE().writeChat();
        }
    }

    public LSE<String> getHistoryMessages() {
        LSE<String> history = new LSE<>();
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            history.addDato(message);
        }
        return history;
    }

    public boolean validateMessagesNull() {
        return messages != null;
    }

}
