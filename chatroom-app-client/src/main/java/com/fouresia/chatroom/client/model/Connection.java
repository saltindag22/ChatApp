package com.fouresia.chatroom.client.model;

import java.net.Socket;

public class Connection {

    private String remoteip;

    private int remoteport;

    private Socket socket;

    public String getRemoteip() {
        return remoteip;
    }

    public void setRemoteip(String remoteip) {
        this.remoteip = remoteip;
    }

    public int getRemoteport() {
        return remoteport;
    }

    public void setRemoteport(int remoteport) {
        this.remoteport = remoteport;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((remoteip == null) ? 0 : remoteip.hashCode());
        result = prime * result + remoteport;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChatConnection other = (ChatConnection) obj;
        if (remoteip == null) {
            if (other.remoteip != null)
                return false;
        } else if (!remoteip.equals(other.remoteip))
            return false;
        if (remoteport != other.remoteport)
            return false;
        return true;
    }

}
