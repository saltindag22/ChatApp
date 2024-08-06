package com.fouresia.chatroom.client.model;

public class EnterRequestedState extends ApplicationState{

    @Override
    public ApplicationState handleEvent(ApplicationEvent event) {
       if (event instanceof EnterOkEvent){
        return handleEnterOkEvent((EnterOkEvent) event);
       }
       return this;
    }

    private InRoomState handleEnterOkEvent(EnterOkEvent event){
        return new InRoomState();
    }
}
