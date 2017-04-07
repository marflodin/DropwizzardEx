package com.marflo.dw.ws.helloworld.helpers;

import java.io.IOException;
import java.net.ServerSocket;

import static org.valid4j.Assertive.neverGetHereError;

public class FreePorts {
    public static int findRandomFreePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        } catch (IOException e) {
            throw neverGetHereError(e, "Could not find a free random port to assign for database stub");
        }
    }
}
