package com.fouresia.chatroom.client.model;

public class RegisterRequestedState extends ApplicationState {

    @Override
    public ApplicationState handleEvent(ApplicationEvent event) {
        if (event instanceof RegisterOkEvent){
            return new handleRegisterOkEvent((RegisterOkEvent)event);
        }
        return this;
    }

    private LogOutState handleRegisterOkEvent(RegisterOkEvent event){
        return new LogOutState();
    }
}
