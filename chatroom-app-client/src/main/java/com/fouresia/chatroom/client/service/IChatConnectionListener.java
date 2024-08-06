package com.fouresia.chatroom.client.service;

public interface IChatConnectionListener {

    String onConnectionMessageReceived(ConnectionService connection, String message);
}
