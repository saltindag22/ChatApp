package com.fouresia.chatroom.client.model;

public class PMRequestedState extends ApplicationState {

    @Override
    public ApplicationState handleEvent(ApplicationEvent event) {
       if (event instanceof PmOkEvent){
        return new InRoomState();
       }
       return this;
    }

}
