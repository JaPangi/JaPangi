package io.github.japangiserver.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class SocketConnectionPool {

    private final List<SocketConnection> pool = new ArrayList<>();

    public void add(SocketConnection connection) {
        pool.add(connection);
        connection.start();
    }

    public void toAllConnections(Consumer<SocketConnection> action) {
        pool.forEach(action::accept);
    }

    public void checkAllConnectionsHealth() {
        List<SocketConnection> unHealthyConnections = pool.stream()
                .filter(connection -> !connection.isHealthyConnection() || connection.isClosedConnection())
                .toList();

        unHealthyConnections.forEach(unHealthyConnection -> {
            unHealthyConnection.closeConnection();
            pool.remove(unHealthyConnection);
        });
    }

    public void closeAll() {
        pool.forEach(SocketConnection::closeConnection);
    }
}
