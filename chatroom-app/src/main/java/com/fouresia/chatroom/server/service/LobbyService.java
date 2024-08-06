package com.fouresia.chatroom.server.service;

import java.util.HashSet;
import java.util.Set;

import com.fouresia.chatroom.server.model.ChatRoom;
import com.fouresia.chatroom.server.model.UserChatSession;

public class LobbyService implements IProtocolLobbyMessageListener {

    private Set<ChatRoom> chatrooms;

    public static LobbyService instance;

    private LobbyService() {
        chatrooms = new HashSet<>();
    }

    public static LobbyService getInstance() {
        if (instance == null) {
            instance = new LobbyService();
        }
        return instance;
    }

    public Set<ChatRoom> getChatRooms() {
        return this.chatrooms;
    }

    public void deleteChatRoom(String chatRoomName) {
        for (ChatRoom chatRoom : chatrooms) {
            if (chatRoom.getName().equals(chatRoomName)) {
                chatrooms.remove(chatRoom);
                // TODO : Delete chatroom from database§
                break;
            }
        }
    }

    @Override
    public void onListUsersMessageReceived(UserChatSession from) {
        StringBuilder sb = new StringBuilder();
        for (ChatRoom chatRoom : chatrooms) {
            Set<UserChatSession> chatRoomUsers = chatRoom.getUsers();
            for (UserChatSession user : chatRoomUsers) {
                sb.append(user);
                sb.append("\n");
            }
        }
        String users = sb.toString();
        from.getConnectionthread().sendMessage(users);
    }

    @Override
    public void onCreateChatroomMessageReceived(UserChatSession from, String chatRoomName) {
        boolean result = chatrooms.add(new ChatRoom(chatRoomName));
        if (result) {
            // TODO : Save chatroom to database
        } else {
            // TODO : Handle already exists error
        }
    }

    @Override
    public void onListChatroomsMessageReceived(UserChatSession from) {
        StringBuilder sb = new StringBuilder();
        for (ChatRoom chatRoom : chatrooms) {
            sb.append(chatRoom.getName());
            sb.append("\n");
        }
        String chatrooms = sb.toString();
        from.getConnectionthread().sendMessage(chatrooms);
    }
}
