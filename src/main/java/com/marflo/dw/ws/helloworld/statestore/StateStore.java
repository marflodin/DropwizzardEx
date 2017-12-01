package com.marflo.dw.ws.helloworld.statestore;

import com.bendb.dropwizard.redis.JedisFactory;
import io.dropwizard.setup.Environment;
import redis.clients.jedis.JedisPool;

public class StateStore {
    JedisPool jedis;

    public StateStore(JedisFactory redis, Environment environment) {
        jedis = redis.build(environment);
    }

    public String get(String key) {
        return jedis.getResource().get(key);
    }

    public void put(String key, String value) {
        jedis.getResource().set(key, value);
    }
}
