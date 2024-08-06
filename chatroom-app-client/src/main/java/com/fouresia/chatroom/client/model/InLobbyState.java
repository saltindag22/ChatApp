package com.fouresia.chatroom.client.model;

public class InLobbyState extends ApplicationState{

    @Override
    public ApplicationState handleEvent(ApplicationEvent event) {
       if (event instanceof CreateCRRequestEvent){
        return this;
       }
       else if(event instanceof DeleteCrRequestEvent){
        return this;
       }
       else if(event instanceof EnterRequestedEvent){
        return handleEnterRequestedEvent((EnterRequestedEvent)event);
       }
       else if(event instanceof LogOutRequestedEvent){
        return handleLogOutRequestedEvent((LogOutRequestedEvent)event);
       }
       
       return this;
    }

    private LogOutState handleLogOutRequestedEvent(LogOutRequestedEvent event) {
        return new LogOutState();
    }

    private EnterRequestedState handleEnterRequestedEvent(EnterRequestedEvent event){
        return new EnterRequestedState();
    }


}
