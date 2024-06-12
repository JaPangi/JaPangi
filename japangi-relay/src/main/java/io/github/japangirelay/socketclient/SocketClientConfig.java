package io.github.japangirelay.socketclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketClientConfig {

    private final String host;
    private final int port;

    public SocketClientConfig(
            @Value("${socket.client.host}") String host,
            @Value("${socket.client.port}") int port
    ) {
        this.host = host;
        this.port = port;
    }

    @Bean
    public SocketClient socket() {
        return SocketClient.of(host, port);
    }
}
