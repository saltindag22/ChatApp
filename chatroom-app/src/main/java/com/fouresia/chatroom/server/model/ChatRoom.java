package com.fouresia.chatroom.server.model;

import java.util.Set;

import com.fouresia.chatroom.server.service.UniqueIdGenerator;

public class ChatRoom {

    private String id;

    private String name;

    private Long creationdate;

    private Set<UserChatSession> users;

    public ChatRoom() {
    }

    public ChatRoom(String name) {
        this.id = UniqueIdGenerator.generateUniqueId();
        this.name = name;
        this.creationdate = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Long creationdate) {
        this.creationdate = creationdate;
    }

    public Set<UserChatSession> getUsers() {
        return users;
    }

    public void setUsers(Set<UserChatSession> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        ChatRoom other = (ChatRoom) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
