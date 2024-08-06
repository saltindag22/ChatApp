package com.fouresia.chatroom.server.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import com.fouresia.chatroom.server.model.User;
import com.fouresia.chatroom.server.model.UserChatSession;
import com.fouresia.chatroom.server.model.UserChatSessionStatus;
import com.fouresia.chatroom.server.model.UserType;

public class AuthenticationService implements IProtocolAuthenticationMessageListener {

    private static AuthenticationService instance;

    private AuthenticationService() {

    }

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    @Override
    public boolean onLoginMessageReceived(ChatConnectionThread from, String incUsername, String incPassword) {

        try {
            ResultSet resultSet = DBService.getInstance().select("users",
                    " username='" + incUsername + "' AND password='" + incPassword + "'");
            String id = resultSet.getString("id");
            String username = resultSet.getString("username");
            String displayname = resultSet.getString("displayname");
            String color = resultSet.getString("color");
            UserType type = resultSet.getString("type") == "ADMIN" ? UserType.ADMIN : UserType.USER;

            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setDisplayname(displayname);
            user.setColor(color);
            user.setType(type);

            UserChatSession userChatSession = new UserChatSession();
            userChatSession.setId(UniqueIdGenerator.generateUniqueId());
            userChatSession.setSessionstart(Instant.now().toEpochMilli());
            userChatSession.setUser(user);
            userChatSession.setIp(from.getChatConnection().getSocket().getInetAddress().getHostAddress());
            userChatSession.setPort(from.getChatConnection().getSocket().getPort());
            userChatSession.setStatus(UserChatSessionStatus.ONLINE);
            userChatSession.setLastmessagets(Instant.now().toEpochMilli());

            DBService.getInstance().insert("userchatsessions", new DBValue[] {
                    new DBValue(userChatSession.getId(), DBValueType.STR),
                    new DBValue(userChatSession.getSessionstart() + "", DBValueType.LONG),
                    new DBValue(user.getId(), DBValueType.STR),
                    new DBValue(userChatSession.getIp(), DBValueType.STR),
                    new DBValue(userChatSession.getPort() + "", DBValueType.INT),
                    new DBValue(userChatSession.getStatus().toString(), DBValueType.STR),
                    new DBValue(userChatSession.getLastmessagets() + "", DBValueType.LONG),
            });

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void register(String username, String password, String displayname) throws SQLException {
        // check if username already exists

    }

    @Override
    public boolean onRegisterMessageReceived(ChatConnectionThread from, String username, String password,
            String displayname) {

        ResultSet resultSet = DBService.getInstance().select("users", "username='" + username + "'");
        if (resultSet != null) {
            // username already exists
            return false;
        }

        try {
            DBService.getInstance().insert("users", new DBValue[] {
                    new DBValue(UniqueIdGenerator.generateUniqueId(), DBValueType.STR),
                    new DBValue(username, DBValueType.STR),
                    new DBValue(password, DBValueType.STR),
                    new DBValue(RandomColorGenerator.getRandomColor(), DBValueType.STR),
                    new DBValue(UserType.USER.toString(), DBValueType.STR),
                    new DBValue(displayname, DBValueType.STR),
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onLogoutMessageReceived(UserChatSession from, String username) {
        // TODO Auto-generated method stub
        return true;
    }

}
