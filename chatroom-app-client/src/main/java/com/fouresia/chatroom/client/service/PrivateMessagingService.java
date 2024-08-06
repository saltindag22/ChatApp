package com.fouresia.chatroom.client.service;

import com.fouresia.chatroom.client.model.ApplicationEvent;
import com.fouresia.chatroom.client.model.PmOkEvent;

public class PrivateMessagingService implements IProtocolPrivateMessageListener {

    private static StateMachine stateMachine;
    private ApplicationEvent PmOkEvent;

    private static PrivateMessagingService instance;

    public static PrivateMessagingService getInstance() {
        if (instance == null) {
            instance = new PrivateMessagingService();
        }
        return instance;
    }
    @Override
    public String onPrivateMessageReceived(ConnectionService from, String other_message) {
       String other = other_message.split(",")[0];
       String message = other_message.split(",")[1];
       String server = String.format("PM/<{}>/<{}>", other, message);
       stateMachine.getCurrentState().handleEvent(PmOkEvent);
       return server;
    }

}
