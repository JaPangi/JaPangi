package io.github.japangiserver.socket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class SocketConnection extends Thread {

    private final SocketSession session;
    private final SocketMessageHandler messageHandler;
    private SocketConnectionStatus status;

    enum SocketConnectionStatus {
        CONNECTED, CLOSED;
    }

    public static SocketConnection of(
            Socket socket,
            SocketMessageHandler messageHandler
    ) {
        return new SocketConnection(
                SocketSession.of(socket),
                messageHandler,
                SocketConnectionStatus.CONNECTED
        );
    }

    @Override
    public void run() {
        afterConnectionEstablished();
        while (status == SocketConnectionStatus.CONNECTED) {
            socketAction();
        }
        afterConnectionClosed();
        this.interrupt();
    }

    private void afterConnectionEstablished() {
        MDC.put("connection_id", session.getSessionId());
        log.info("[{}] connection created (server :{})", session.getSessionId(), session.getLocalPort());
    }

    private void socketAction() {
        String message = session.readMessage();
        if (message != null) {
            checkSystemExitSignal(message);
            messageHandler.handleSocketMessage(message, this);
        }
    }

    private void checkSystemExitSignal(String message) {
        if (message.contains("SYSTEM_EXIT")) {
            closeConnection();
        }
    }

    private void afterConnectionClosed() {
        log.info("[{}] connection closed (server :{})", session.getSessionId(), session.getLocalPort());
        MDC.clear();
    }

    public void write(String message) {
        session.writeMessage(message);
    }

    public void closeConnection() {
        this.status = SocketConnectionStatus.CLOSED;
        session.close();
    }

    public boolean isHealthyConnection() {
        return session.sendPing();
    }

    public boolean isClosedConnection() {
        return status.equals(SocketConnectionStatus.CLOSED);
    }

    public boolean isSameConnection(String targetId) {
        return session.getSessionId().equals(targetId);
    }
}
