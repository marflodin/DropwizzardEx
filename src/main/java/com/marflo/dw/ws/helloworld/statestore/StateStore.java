package com.marflo.dw.ws.helloworld.statestore;

import java.util.HashMap;

public class StateStore {
    private HashMap<String, String> store = new HashMap<>();

    public String get(String key) {
        return store.get(key);
    }

    public void put(String key, String value) {
        store.put(key, value);
    }
}
