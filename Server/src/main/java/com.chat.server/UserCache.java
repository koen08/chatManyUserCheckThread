package com.chat.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserCache {
    private final List<ClientServiceThread> users = new CopyOnWriteArrayList<>();

    public void add(ClientServiceThread clientServiceThread) {
        users.add(clientServiceThread);
    }

    public ClientServiceThread get(int index) {
        return users.get(index);
    }

    public int size() {
        return users.size();
    }
}
