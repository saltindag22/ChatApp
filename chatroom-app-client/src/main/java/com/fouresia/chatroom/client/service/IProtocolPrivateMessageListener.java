package com.fouresia.chatroom.client.service;

public interface IProtocolPrivateMessageListener extends IProtocolMessageListener {

    String onPrivateMessageReceived(ConnectionService from, String other_message);
}
