/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ChatStatus;
import util.LSE;

/**
 *
 * @author joanp
 */
public class Chat {

    private final LSE<String> messages;
    private ChatStatus state;

    public Chat() {
        this.state = ChatStatus.INACTIVE;
        this.messages = new LSE<>();
    }

    public ChatStatus getState() {
        return state;
    }

    public void setState(ChatStatus state) {
        this.state = state;
    }

    public LSE<String> getMessages() {
        return messages;
    }

}
