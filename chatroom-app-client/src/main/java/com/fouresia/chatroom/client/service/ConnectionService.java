package com.fouresia.chatroom.client.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.fouresia.chatroom.client.model.ApplicationEvent;
import com.fouresia.chatroom.client.model.ApplicationState;
import com.fouresia.chatroom.client.model.Connection;

public class ConnectionService extends Thread {

    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;

    private Connection connection;

    private List<IChatConnectionListener> listeners = new ArrayList<>();

    
    private List<ApplicationState> states = new ArrayList<>();
    
    
    private static StateMachine stateMachine = StateMachine.getInstance();

    private ApplicationState currentState;

    public ConnectionService(Socket socket) {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                String message = bufferedReader.readLine();
                if (message == null) {
                    break;
                }
                notifyEvents(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribeEvents(ApplicationState newState){
        this.states.add(newState);
    }

    public String notifyEvents(String message) {     //////////?????????????
        for (IChatConnectionListener listener : this.listeners) {
            return listener.onConnectionMessageReceived(this, message);
        }
        return message;
        
    }
}
