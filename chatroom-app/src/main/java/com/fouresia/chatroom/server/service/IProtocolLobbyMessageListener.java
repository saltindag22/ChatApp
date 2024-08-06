package com.fouresia.chatroom.server.service;

import com.fouresia.chatroom.server.model.UserChatSession;

public interface IProtocolLobbyMessageListener extends IProtocolMessageListener {

    void onListUsersMessageReceived(UserChatSession from);

    void onCreateChatroomMessageReceived(UserChatSession from, String chatRoomName);

    void onListChatroomsMessageReceived(UserChatSession from);
}
