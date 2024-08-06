package com.fouresia.chatroom.server;

import com.fouresia.chatroom.server.service.ChatRoomServivce;
import com.fouresia.chatroom.server.service.ConnectionHandlerService;
import com.fouresia.chatroom.server.service.LobbyService;
import com.fouresia.chatroom.server.service.PrivateMessagingService;
import com.fouresia.chatroom.server.service.ProtocolService;

public class Main {
    public static void main(String[] args) {

        {
            ProtocolService.getInstance().addListener(ChatRoomServivce.getInstance());
            ProtocolService.getInstance().addListener(LobbyService.getInstance());
            ProtocolService.getInstance().addListener(PrivateMessagingService.getInstance());
        }

        ConnectionHandlerService connectionHandlerService = new ConnectionHandlerService();
        connectionHandlerService.startListening();
    }
}