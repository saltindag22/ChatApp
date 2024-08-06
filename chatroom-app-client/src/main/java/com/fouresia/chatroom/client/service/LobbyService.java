package com.fouresia.chatroom.client.service;

import com.fouresia.chatroom.client.model.ApplicationEvent;
import com.fouresia.chatroom.client.model.EnterOkEvent;

public class LobbyService implements IProtocolLobbyMessageListener{

      private static StateMachine stateMachine;
      private ApplicationEvent EnterOkEvent;
      private static LobbyService instance;

      public static LobbyService getInstance() {
          if (instance == null) {
              instance = new LobbyService();
          }
          return instance;
      }

    @Override
    public String onListUsersMessageReceived(ConnectionService from, String message) {
      String server = "LULO";  ///////////////
      return server;
    }

    @Override
    public String onCreateChatroomMessageReceived(ConnectionService from, String chatroomName) {
       String server = String.format("CCR/<{}>", chatroomName);
       return server;
    }

    @Override
    public String onDeleteChatroomMessageReceived(ConnectionService from, String chatroomName) {
       String server = String.format("DCR/<{}>",chatroomName);
       return server;
    }

    @Override
    public String onListChatroomMessageReceived(ConnectionService from, String message) {
       String server = String.format("LST");/////////////////////////
       return server;
    }

    @Override
    public String onEnterChatRoomMessageReceived(ConnectionService from, String chatroomName) {
      String server = String.format("ENT/<{}>", chatroomName);
      stateMachine.getCurrentState().handleEvent(EnterOkEvent);
      return server;
    }

}
