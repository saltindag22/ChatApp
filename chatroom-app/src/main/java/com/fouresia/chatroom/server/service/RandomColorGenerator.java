package com.fouresia.chatroom.server.service;

import java.util.Random;

public class RandomColorGenerator {

    public static String getRandomColor(){
     Random random = new Random();
     String color = String.format("%02X%02X%02X", random.nextInt(256), random.nextInt(256), random.nextInt(256));
     return color;
}
}
