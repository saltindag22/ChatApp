package com.fouresia.chatroom.server.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.fouresia.chatroom.server.model.ChatConnection;

public class ChatConnectionThread extends Thread {

    private ChatConnection chatConnection;

    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;

    private List<IChatConnectionListener> listeners = new ArrayList<>();

    public ChatConnectionThread(ChatConnection chatConnection) throws IOException {
        this.chatConnection = chatConnection;
        this.bufferedReader = new BufferedReader(new InputStreamReader(chatConnection.getSocket().getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(chatConnection.getSocket().getOutputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = bufferedReader.readLine();
                if (message == null) {
                    break;
                }
                notifyListeners(message);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }

    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChatConnection getChatConnection() {
        return chatConnection;
    }

    public void setChatConnection(ChatConnection chatConnection) {
        this.chatConnection = chatConnection;
    }

    public void notifyListeners(String message) {
        for (IChatConnectionListener listener : this.listeners) {
            listener.onChatConnectionMessageReceived(this, message);
        }
    }

    public void subscribe(IChatConnectionListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chatConnection == null) ? 0 : chatConnection.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChatConnectionThread other = (ChatConnectionThread) obj;
        if (chatConnection == null) {
            if (other.chatConnection != null)
                return false;
        } else if (!chatConnection.equals(other.chatConnection))
            return false;
        return true;
    }

}
