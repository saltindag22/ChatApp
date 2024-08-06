package com.fouresia.chatroom.server.service;

import java.util.ArrayList;
import java.util.List;

import com.fouresia.chatroom.server.model.ChatConnection;
import com.fouresia.chatroom.server.model.UserChatSession;

public class ProtocolService implements IChatConnectionListener {

    private List<IProtocolMessageListener> listeners = new ArrayList<>();

    private static ProtocolService instance;

    public static ProtocolService getInstance() {
        if (instance == null) {
            instance = new ProtocolService();
        }
        return instance;
    }

    @Override
    public void onChatConnectionMessageReceived(ChatConnectionThread connection, String message) {

        UserChatSession fromSession = UserChatSessionService.getInstance()
                .getUserChatSessionByConnection(connection.getChatConnection());

        for (IProtocolMessageListener listener : listeners) {
            if (listener instanceof IProtocolPrivateMessageListener) {
                if (message.startsWith("PM/")) {
                    if (fromSession == null) {
                        continue;
                    }
                    ((IProtocolPrivateMessageListener) listener).onPrivateMessageReceived(fromSession,
                            message.split("/")[1],
                            message.split("/")[2]);
                } else if (message.startsWith("LLU/")) {
                    ((IProtocolLobbyMessageListener) listener).onListUsersMessageReceived(fromSession);
                } else if (message.startsWith("LOG/")) {
                    // LOG/erdem/password123
                    String username = message.split("/")[1];
                    String password = message.split("/")[2];
                    boolean result = ((IProtocolAuthenticationMessageListener) listener).onLoginMessageReceived(
                            connection, username,
                            password);
                    if (result) {
                        fromSession.getConnectionthread().sendMessage("LOG/OK");
                    } else {
                        fromSession.getConnectionthread().sendMessage("LOG/FAIL");
                    }

                } else if (message.startsWith("REG/")) {

                }
            }
        }
    }

    public void addListener(IProtocolMessageListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(IProtocolMessageListener listener) {
        this.listeners.remove(listener);
    }

    // "PM/erdem/Hello"
    // "LLU/"
    // "LRU/"

}
