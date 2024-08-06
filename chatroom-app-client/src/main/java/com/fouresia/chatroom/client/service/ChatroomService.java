package com.fouresia.chatroom.client.service;

import com.fouresia.chatroom.client.model.ApplicationEvent;
import com.fouresia.chatroom.client.model.ExitCrOkEvent;

public class ChatroomService implements IProtocolChatroomMessageListener {

    private static StateMachine stateMachine;
    private ApplicationEvent ExitCrOkEvent;
    private static ChatroomService instance;

    public static ChatroomService getInstance() {
        if (instance == null) {
            instance = new ChatroomService();
        }
        return instance;
    }

    @Override
    public String onChatroomMessageReceived(ConnectionService from, String chatroomName_message) {
        String chatroomName = chatroomName_message.split(",")[0];
        String message = chatroomName_message.split(",")[1];
        String server = String.format("RM/<{}>/<{}>", chatroomName,message);
        return server;
    }

    @Override
    public String onChatroomExitMessageReceived(ConnectionService from) {
        String server = "ECR";
        stateMachine.getCurrentState().handleEvent(ExitCrOkEvent);
        return server;
    }

    @Override
    public String onChatroomListUsersMessageReceived(ConnectionService from, String chatroomName) {
        String server = "LUCR"
        return server;
    }

}
