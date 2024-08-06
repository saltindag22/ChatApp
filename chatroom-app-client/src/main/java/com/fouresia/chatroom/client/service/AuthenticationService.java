package com.fouresia.chatroom.client.service;

import com.fouresia.chatroom.client.model.ApplicationEvent;
import com.fouresia.chatroom.client.model.LogOutRequestedEvent;
import com.fouresia.chatroom.client.model.LoginOkEvent;
import com.fouresia.chatroom.client.model.RegisterOkEvent;

public class AuthenticationService implements IProtocolAuthenticationListener{

    private ApplicationEvent LoginOkEvent;
    private ApplicationEvent RegisterOkEvent;
    private ApplicationEvent LogOutRequestedEvent;
    private static StateMachine stateMachine;

    private static AuthenticationService instance;

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    @Override
    public String onLoginMessageReceived(ConnectionService from, String username_password) {
       String username = username_password.split(",")[0];
       String password = username_password.split(",")[1];
       String server = String.format("LOG/<{}>/<{}>",username,password);
       stateMachine.getCurrentState().handleEvent(LoginOkEvent); 
       return server;
    }

    @Override
    public String onRegisterMessageReceived(ConnectionService from, String name_pass_display) {
       String username = name_pass_display.split(",")[0];
       String password = name_pass_display.split(",")[1];
       String displayname = name_pass_display.split(",")[2];
       String type = name_pass_display.split(",")[3];
       String server = String.format("REG/<{}>/<{}>/<{}>/<{}>", username,password,displayname,type);
       stateMachine.getCurrentState().handleEvent(RegisterOkEvent);
       return server;
       
    }

    @Override
    public String onLogoutMessageReceived(ConnectionService from, String username) {
      String server = "LOGOUT";
      stateMachine.getCurrentState().handleEvent(LogOutRequestedEvent);
      return server;
    }


}
