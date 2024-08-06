package com.fouresia.chatroom.client.service;

public interface IProtocolChatroomMessageListener extends IProtocolMessageListener {

    String onChatroomMessageReceived(ConnectionService from, String chatroomName_message);

    String onChatroomExitMessageReceived(ConnectionService from);

    String onChatroomListUsersMessageReceived(ConnectionService from, String chatroomName);
}
