package com.fouresia.chatroom.client.service;

import com.fouresia.chatroom.client.model.ApplicationEvent;
import com.fouresia.chatroom.client.model.ApplicationState;

public class StateMachine {

    private static StateMachine instance;

    public static ApplicationState currentState;

    StateMachine() {

    }

    public ApplicationState getCurrentState() {
        return currentState;
        
    }

    public void setCurrentState(ApplicationState currentState) {
        this.currentState = currentState;
        
    }

    public static StateMachine getInstance() {
        if (instance == null) {
            instance = new StateMachine();
        }
        return instance;
    }

    public void handleEvent(ApplicationEvent event) {
        setCurrentState(getCurrentState().handleEvent(event));

    }
}
