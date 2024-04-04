package io.github.japangiserver.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.usecase.UseCaseProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
@RequiredArgsConstructor
@Slf4j
public class SocketConnectionHandler implements ApplicationRunner {

    private final ServerSocket serverSocket;
    private final UseCaseProvider useCaseProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread connectionHandlingThread = new Thread(handleSocketConnection());
        connectionHandlingThread.start();
    }

    private Runnable handleSocketConnection() {
        return () -> {
            log.info("[:{}] Socket opened", serverSocket.getLocalPort());
            try {
                while (true) {
                    Socket socket = serverSocket.accept();
                    SocketMessageHandler connection = new SocketMessageHandler(socket, useCaseProvider, objectMapper);
                    connection.start();
                }
            } catch (IOException e) {
                log.info("[:{}] Socket closed", serverSocket.getLocalPort());
            }
        };
    }
}
