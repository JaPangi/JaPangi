package io.github.japangiserver.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketHealthCheckHandler implements ApplicationRunner {

    private static final int HEALTH_CHECK_TERM = 30000;

    private final SocketConnectionPool connectionPool;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread healthCheckHandlingThread = new Thread(handleSocketHealthCheck());
        healthCheckHandlingThread.start();
    }

    private Runnable handleSocketHealthCheck() {
        return () -> {
            while (true) {
                waitForHealthCheckTerm();
                connectionPool.checkAllConnectionsHealth();
            }
        };
    }

    private void waitForHealthCheckTerm() {
        try {
            Thread.sleep(HEALTH_CHECK_TERM);
        } catch (InterruptedException e) {
            throw new RuntimeException("cannot wait");
        }
    }
}
