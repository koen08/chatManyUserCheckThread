package com.chat.client;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 500; i++) {
            ClientThread clientThread = new ClientThread();
            clientThread.start();
        }
    }
}
