package com.fouresia.chatroom.client.model;

public abstract class ApplicationState {

    public abstract ApplicationState handleEvent(ApplicationEvent event);
}

