package com.fouresia.chatroom.server.service;

import java.util.HashMap;
import java.util.Map;

import com.fouresia.chatroom.server.model.UserChatSession;

public class PrivateMessagingService implements IProtocolPrivateMessageListener {

    private static PrivateMessagingService instance;

    private PrivateMessagingService() {
    }

    public static PrivateMessagingService getInstance() {
        if (instance == null) {
            instance = new PrivateMessagingService();
        }
        return instance;
    }

    private Map<String, ChatConnectionThread> privateConnections = new HashMap<>();

    @Override
    public void onPrivateMessageReceived(UserChatSession from, String username, String message) {

    }
}
