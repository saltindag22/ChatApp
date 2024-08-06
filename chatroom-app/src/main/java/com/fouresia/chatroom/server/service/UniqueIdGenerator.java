package com.fouresia.chatroom.server.service;

import java.util.UUID;

public class UniqueIdGenerator {
    public static String generateUniqueId() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(24);
    }
}
