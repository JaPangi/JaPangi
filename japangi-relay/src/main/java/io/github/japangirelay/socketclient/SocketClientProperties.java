package io.github.japangirelay.socketclient;

import org.springframework.beans.factory.annotation.Value;

public class SocketClientProperties {

    private final String host;
    private final int port;

    public SocketClientProperties(
            @Value("socket.client.host") String host,
            @Value("socket.client.port") int port
    ) {
        this.host = host;
        this.port = port;
    }
}
