package com.fouresia.chatroom.server.model;

import java.beans.Statement;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Lobby {

    public static void lobby(String username){

        Scanner scanner = new Scanner(System.in);
        System.out.printf("Now, you are in the Lobby%n 1- chatroom%n 2- users%n");
        String option = scanner.nextLine();
        if (option=="chatroom list"){

            // ChatRoom table dan chatroom nameleri listele
            try {
                Connection connection = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","mydb","semoCan14!");
                String sql = "SELECT namess FROM ChatRoom";
                java.sql.Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    String name = rs.getString("namess");
                    System.out.println(name);
                }
    
                // Bağlantıyı kapat
                rs.close();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
        else if(option=="chatroom enter < nameofthechatroom>"){
            // // enter chatroom
            // Socket socket = new Socket("localhost", 6868);
            // Client client = new Client(socket,username);
            // client.listenForMessage();
            // client.sendMessage();

            
        }
        // else if (username is admin and option=="chatroom create < chatroomname>"){

        // }
        // else if (username is admin and option== "chatroom delete <chatroomname<"){

        // }
        else if (option=="list-users"){
            // list all ONLINE users from users table
        }
       
    }

}
