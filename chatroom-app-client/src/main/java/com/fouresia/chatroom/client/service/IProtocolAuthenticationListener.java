package com.fouresia.chatroom.client.service;

public interface IProtocolAuthenticationListener extends IProtocolMessageListener {

    String onLoginMessageReceived(ConnectionService from, String username_password);

    String onRegisterMessageReceived(ConnectionService from, String name_pass_display);

    String onLogoutMessageReceived(ConnectionService from, String username);
}
