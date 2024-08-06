package com.fouresia.chatroom.client.model;

public class LoginRequestedState extends ApplicationState {

    @Override
    public ApplicationState handleEvent(ApplicationEvent event) {
        if (event instanceof LoginOkEvent){
            return new handleLoginOkEvent((LoginOkEvent) event);
        }
        return this;
    }

    private InLobbyState handleLoginOkEvent(LoginOkEvent event){
        return  new InLobbyState();
    }
}
