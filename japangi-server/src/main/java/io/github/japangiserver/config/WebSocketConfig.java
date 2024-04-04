package io.github.japangiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class WebSocketConfig {

    private final int socketPort;

    public WebSocketConfig(
            @Value("${socket.port}") int socketPort
    ) {
        this.socketPort = socketPort;
    }

    @Bean
    public ServerSocket serverSocket() {
        try {
            return new ServerSocket(socketPort);
        } catch (IOException e) {
            throw new RuntimeException("cannot open socket server");
        }
    }
}
