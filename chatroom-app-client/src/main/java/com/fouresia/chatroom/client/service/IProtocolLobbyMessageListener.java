package com.fouresia.chatroom.client.service;

public interface IProtocolLobbyMessageListener extends IProtocolMessageListener{

    String onListUsersMessageReceived(ConnectionService from, String message);

    String onCreateChatroomMessageReceived(ConnectionService from, String chatroomName);

    String onDeleteChatroomMessageReceived(ConnectionService from, String chatroomName);

    String onListChatroomMessageReceived(ConnectionService from, String message);

    String onEnterChatRoomMessageReceived(ConnectionService from, String chatroomName);

    
}
