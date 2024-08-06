package com.fouresia.chatroom.server.service;

import java.util.HashSet;
import java.util.Set;

import com.fouresia.chatroom.server.model.ChatConnection;
import com.fouresia.chatroom.server.model.UserChatSession;

public class UserChatSessionService {

    private Set<UserChatSession> sessions;

    private static UserChatSessionService instance;

    private UserChatSessionService() {
        this.sessions = new HashSet<>();
        // Load Sessions from DB.
    }

    public static UserChatSessionService getInstance() {
        if (instance == null) {
            instance = new UserChatSessionService();
        }
        return instance;
    }

    public Set<UserChatSession> getUserChatSessions() {
        return this.sessions;
    }

    public void deleteUserChatSession(UserChatSession userChatSession) {
        this.sessions.remove(userChatSession);
        // Delete userChatSession from DB.
    }

    public void addUserChatSession(UserChatSession userChatSession) {
        this.sessions.add(userChatSession);
        // Save userChatSession to DB.
    }

    public UserChatSession getUserChatSession(String username) {
        for (UserChatSession userChatSession : sessions) {
            if (userChatSession.getUser().getUsername().equals(username)) {
                return userChatSession;
            }
        }
        return null;
    }

    public UserChatSession getUserChatSessionByConnection(ChatConnection connection) {
        for (UserChatSession userChatSession : sessions) {
            if (userChatSession.getConnectionthread().getChatConnection().equals(connection)) {
                return userChatSession;
            }
        }
        return null;
    }

}
