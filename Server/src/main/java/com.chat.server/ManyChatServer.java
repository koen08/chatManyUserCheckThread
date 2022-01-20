package com.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ManyChatServer extends Server {
    private Log log = new Log(getClass().getName(), Thread.currentThread().getName());

    @Override
    public void processServer() throws IOException {
        UserCache userCache = new UserCache();
        Socket clientSocket;
        while ((clientSocket = serverSocket.accept()) != null) {
            log.info("Client found...");
            ClientServiceThread clientServiceThread = new ClientServiceThread(userCache, clientSocket);
            userCache.add(clientServiceThread);
            clientServiceThread.start();
            log.info("Count client connected = " + userCache.size());
        }
    }
}
