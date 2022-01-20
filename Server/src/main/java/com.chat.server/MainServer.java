package com.chat.server;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
        Server server = new ManyChatServer();
        server.start();
    }
}
