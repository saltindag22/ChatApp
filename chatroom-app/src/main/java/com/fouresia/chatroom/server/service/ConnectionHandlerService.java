package com.fouresia.chatroom.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.fouresia.chatroom.server.model.ChatConnection;

public class ConnectionHandlerService {

    private ServerSocket serversocket;

    public ConnectionHandlerService() {
        try {
            serversocket = new ServerSocket(6868);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
        System.out.println("Server is listening...");

        try {
            while (!serversocket.isClosed()) {
                Socket newConnctionRequestSocket = serversocket.accept();

                System.out.println("A new client has connected with ip:port => "
                        + newConnctionRequestSocket.getInetAddress().getHostAddress() + ":"
                        + newConnctionRequestSocket.getPort());
                ChatConnection cConnection = new ChatConnection();
                cConnection.setRemoteip(newConnctionRequestSocket.getInetAddress().getHostAddress());
                cConnection.setRemoteport(newConnctionRequestSocket.getPort());

                ChatConnectionThread chatConnectionThread = new ChatConnectionThread(cConnection);
                chatConnectionThread.subscribe(ProtocolService.getInstance());
                chatConnectionThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeServerSocket() {
        try {
            if (serversocket != null) {
                serversocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
