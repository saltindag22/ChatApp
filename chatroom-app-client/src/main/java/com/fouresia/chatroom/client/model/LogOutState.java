package com.fouresia.chatroom.client.model;

public class LogOutState extends ApplicationState {

    @Override
    public ApplicationState handleEvent(ApplicationEvent event) {
        if (event instanceof LoginRequestEvent){
            return handleLoginRequestEvent((LoginRequestEvent) event);
        }
        else if(event instanceof RegisterRequestEvent){
            return handleRegisterRequestEvent((RegisterRequestEvent)event);
        }
        return this;
    }

   

    private RegisterRequestedState handleRegisterRequestEvent(RegisterRequestEvent event) {
        return new RegisterRequestedState();
    }



    private LoginRequestedState handleLoginRequestEvent(LoginRequestEvent event) {
        return new LoginRequestedState();
    }
}
