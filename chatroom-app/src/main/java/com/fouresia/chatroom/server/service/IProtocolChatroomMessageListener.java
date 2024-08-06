package com.fouresia.chatroom.server.service;

import com.fouresia.chatroom.server.model.UserChatSession;

public interface IProtocolChatroomMessageListener extends IProtocolMessageListener {

    void onChatroomMessageReceived(UserChatSession from, String chatRoomName, String message);

    void onChatroomExitMessageReceived(UserChatSession from);

    void onChatroomListUsersMessageReceived(UserChatSession from, String chatRoomName);
}
