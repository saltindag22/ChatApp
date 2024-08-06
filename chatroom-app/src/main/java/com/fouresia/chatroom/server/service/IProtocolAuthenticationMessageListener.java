package com.fouresia.chatroom.server.service;

import com.fouresia.chatroom.server.model.UserChatSession;

public interface IProtocolAuthenticationMessageListener extends IProtocolMessageListener {

    boolean onLoginMessageReceived(ChatConnectionThread from, String username, String password);

    boolean onRegisterMessageReceived(ChatConnectionThread from, String username, String password, String displayname);

    boolean onLogoutMessageReceived(UserChatSession from, String username);
}
