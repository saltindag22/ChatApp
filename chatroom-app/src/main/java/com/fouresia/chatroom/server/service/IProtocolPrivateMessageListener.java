package com.fouresia.chatroom.server.service;

import com.fouresia.chatroom.server.model.UserChatSession;

public interface IProtocolPrivateMessageListener extends IProtocolMessageListener {
    void onPrivateMessageReceived(UserChatSession from, String username, String message);
}
