package io.github.japangirelay.socketclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketClientConfig {
    @Bean
    public SocketClient socket() {
        return SocketClient.of("127.0.0.1", 8070);
    }
}
