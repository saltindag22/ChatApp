package com.fouresia.chatroom.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.fouresia.chatroom.client.service.AuthenticationService;
import com.fouresia.chatroom.client.service.ChatroomService;
import com.fouresia.chatroom.client.service.ConnectionService;
import com.fouresia.chatroom.client.service.LobbyService;
import com.fouresia.chatroom.client.service.PrivateMessagingService;
import com.fouresia.chatroom.client.service.ProtocolService;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {


         {
            ProtocolService.getInstance().addListener(AuthenticationService.getInstance());
            ProtocolService.getInstance().addListener(ChatroomService.getInstance());
            ProtocolService.getInstance().addListener(LobbyService.getInstance());
            ProtocolService.getInstance().addListener(PrivateMessagingService.getInstance());
        }

        String welcomeString = "";
        System.out.println(welcomeString);

        Socket outgoingSocket = new Socket("localhost", 6868);

        ConnectionService connectionService = new ConnectionService(outgoingSocket);
        connectionService.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String parsedMessage = connectionService.notifyEvents(userInput); //// or run()????
            connectionService.sendMessage(parsedMessage);

        }

    }
}