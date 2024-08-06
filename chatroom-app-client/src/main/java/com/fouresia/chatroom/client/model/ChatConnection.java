package com.fouresia.chatroom.client.model;

import java.net.Socket;

public class ChatConnection {

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
