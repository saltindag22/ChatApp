package com.fouresia.chatroom.client.model;

public class InRoomState extends ApplicationState {

    @Override
    public ApplicationState handleEvent( ApplicationEvent event) {
        if (event instanceof ExitCrOkEvent){
            return handleExitCrOkEvent((ExitCrOkEvent) event);
        }
        else if (event instanceof PmRequestEvent){
            return handlePMRequestEvent((PmRequestEvent) event);
        }
        else if (event instanceof LogOutRequestedEvent){
            return handleLogOutRequestedEvent((LogOutRequestedEvent) event);
        }
        return this;
      
    }

    private LogOutState handleLogOutRequestedEvent(LogOutRequestedEvent event) {
        return new LogOutState();
    }

    private ApplicationState handlePMRequestEvent(PmRequestEvent event) {
        return new PMRequestedState();
    }

    private InLobbyState handleExitCrOkEvent(ExitCrOkEvent event){
        return new InLobbyState();
    }
    

}
