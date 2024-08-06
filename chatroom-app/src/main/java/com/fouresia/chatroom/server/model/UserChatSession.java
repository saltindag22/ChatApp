package com.fouresia.chatroom.server.model;

import com.fouresia.chatroom.server.service.ChatConnectionThread;

public class UserChatSession {

    private String id;

    private Long sessionstart;

    private User user;

    private String ip;

    private int port;

    private UserChatSessionStatus status;

    private Long lastmessagets;

    private ChatConnectionThread connectionthread;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSessionstart() {
        return sessionstart;
    }

    public void setSessionstart(Long sessionstart) {
        this.sessionstart = sessionstart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public UserChatSessionStatus getStatus() {
        return status;
    }

    public void setStatus(UserChatSessionStatus status) {
        this.status = status;
    }

    public Long getLastmessagets() {
        return lastmessagets;
    }

    public void setLastmessagets(Long lastmessagets) {
        this.lastmessagets = lastmessagets;
    }

    public ChatConnectionThread getConnectionthread() {
        return connectionthread;
    }

    public void setConnectionthread(ChatConnectionThread connectionthread) {
        this.connectionthread = connectionthread;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(status == UserChatSessionStatus.ONLINE ? "[1] " : "[0] ");
        sb.append(user.getDisplayname());
        return sb.toString();
    }
}
