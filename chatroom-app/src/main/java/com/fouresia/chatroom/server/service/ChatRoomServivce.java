package com.fouresia.chatroom.server.service;

import java.util.Set;

import com.fouresia.chatroom.server.model.ChatRoom;
import com.fouresia.chatroom.server.model.UserChatSession;

public class ChatRoomServivce implements IProtocolChatroomMessageListener {

    private static ChatRoomServivce instance;

    private ChatRoomServivce() {
    }

    public static ChatRoomServivce getInstance() {
        if (instance == null) {
            instance = new ChatRoomServivce();
        }
        return instance;
    }

    @Override
    public void onChatroomMessageReceived(UserChatSession from, String chatRoomName, String message) {
        Set<ChatRoom> rooms = LobbyService.getInstance().getChatRooms();
        ChatRoom chatRoom = null;
        for (ChatRoom room : rooms) {
            if (room.getName().equals(chatRoomName)) {
                chatRoom = room;
                break;
            }
        }
        if (chatRoom == null) {
            System.out.println("Could not find the chatroom with name : " + chatRoomName);
            return;
        }
        if (chatRoom != null) {
            chatRoom.getUsers().forEach(user -> {
                ChatConnectionThread fromThread = from.getConnectionthread();
                ChatConnectionThread ccThread = user.getConnectionthread();
                if (fromThread == ccThread) {
                    return;
                }
                ccThread.sendMessage(message);
            });
        }
    }

    @Override
    public void onChatroomExitMessageReceived(UserChatSession from) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChatroomExitMessageReceived'");
    }

    @Override
    public void onChatroomListUsersMessageReceived(UserChatSession from, String chatRoomName) {
        Set<ChatRoom> rooms = LobbyService.getInstance().getChatRooms();
        ChatRoom chatRoom = null;
        for (ChatRoom room : rooms) {
            if (room.getName().equals(chatRoomName)) {
                chatRoom = room;
                break;
            }
        }
        if (chatRoom == null) {
            System.out.println("Could not find the chatroom with name : " + chatRoomName);
            return;
        }
        ChatConnectionThread fromThread = from.getConnectionthread();
        if (chatRoom != null) {
            Set<UserChatSession> users = chatRoom.getUsers();
            StringBuilder sb = new StringBuilder();
            for (UserChatSession user : users) {
                sb.append(user);
                sb.append("\n");
            }
            fromThread.sendMessage(sb.toString());
        }
    }
}
