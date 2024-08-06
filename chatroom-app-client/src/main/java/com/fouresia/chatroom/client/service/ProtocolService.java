package com.fouresia.chatroom.client.service;

import java.util.ArrayList;
import java.util.List;

import com.fouresia.chatroom.client.model.ApplicationEvent;
import com.fouresia.chatroom.client.model.InLobbyState;
import com.fouresia.chatroom.client.model.InRoomState;
import com.fouresia.chatroom.client.model.LogOutState;
import com.fouresia.chatroom.client.model.PMRequestedState;

public class ProtocolService implements IChatConnectionListener {
    
    private List<IProtocolMessageListener> listeners = new ArrayList<>();

    private String incMessage;

    private static StateMachine stateMachine;

    private static ProtocolService instance;

    public static ProtocolService getInstance() {
        if (instance == null) {
            instance = new ProtocolService();
        }
        return instance;
    }

    @Override
    public String onConnectionMessageReceived(ConnectionService connection, String message) {
        /*UserChatSession fromSession = UserChatSessionService.getInstance()
        .getUserChatSessionByConnection(connection.getChatConnection());*/

        for (IProtocolMessageListener listener : listeners) {
                if(stateMachine.getCurrentState() instanceof LogOutState){
                    if(message.matches("^\\w+\\s*,\\s*\\w+$")){
                        return ((IProtocolAuthenticationListener) listener).onLoginMessageReceived(connection, message);
                    }
                    else if (message.matches("^\\w+\\s*,\\s*\\w+\\s*,\\s*\\w+\\s*,\\s*\\w+$")){
                        return ((IProtocolAuthenticationListener) listener).onRegisterMessageReceived(connection,  message);
                    }
                }
                else if(stateMachine.getCurrentState() instanceof InLobbyState){
                    if(message.startsWith("create")){
                       return ((IProtocolLobbyMessageListener)listener).onCreateChatroomMessageReceived(connection, message);
                    }
                    else if( message.startsWith("delete")){
                       return ((IProtocolLobbyMessageListener)listener).onDeleteChatroomMessageReceived(connection, message);
                    }
                    else if(message.startsWith("list users")){
                       return ((IProtocolLobbyMessageListener)listener).onListUsersMessageReceived(connection,message);
                    }
                }
                else if(stateMachine.getCurrentState() instanceof InRoomState){
                    if (message.startsWith("sendcr")){
                        return((IProtocolChatroomMessageListener)listener).onChatroomMessageReceived(connection,  message);
                    }
                    else if (message.startsWith("exit")){
                       return ((IProtocolChatroomMessageListener)listener).onChatroomExitMessageReceived(connection);
                    }
                    else if(message.startsWith("list users")){
                       return ((IProtocolChatroomMessageListener)listener).onChatroomListUsersMessageReceived(connection, message);
                    }
                }
                else if(stateMachine.getCurrentState() instanceof PMRequestedState){
                   return ((IProtocolPrivateMessageListener)listener).onPrivateMessageReceived(connection,message);
                }




}
        return message;

    }
    public void addListener(IProtocolMessageListener listener) {
        this.listeners.add(listener);
    }


    public void removeListener(IProtocolMessageListener listener) {
        this.listeners.remove(listener);
    }

    
    public List<IProtocolMessageListener> getListeners() {
        return listeners;
    }

    public String getIncMessage() {
        return incMessage;
    }

}
