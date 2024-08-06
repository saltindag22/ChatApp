package com.fouresia.chatroom.server.service;

public interface IChatConnectionListener {

    void onChatConnectionMessageReceived(ChatConnectionThread connection, String message);
}
