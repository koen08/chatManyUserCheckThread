package com.chat.client;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;

public class ClientThread extends Thread {
    private Log log = new Log(getClass().getName(), Thread.currentThread().getName());
    private final BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final Gson gson;
    private final Random random;

    public ClientThread() throws IOException {
        Socket socket = new Socket("192.168.100.4", 8000);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        gson = new Gson();
        random = new Random();
    }

    @Override
    public void run() {
        try {
            sendMsgToServer("Hello, i here");
            while (!isInterrupted()) {
                String msg = gson.fromJson(bufferedReader.readLine(), String.class);
                log.info(msg);
                ClientThread.sleep(random.nextInt(15));
                sendMsgToServer(UUID.randomUUID().toString());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            interrupt();
        }
    }

    public void sendMsgToServer(String msg) throws IOException {
        bufferedWriter.write(new Gson().toJson(msg));
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
