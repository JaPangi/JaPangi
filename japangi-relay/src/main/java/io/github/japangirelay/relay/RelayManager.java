package io.github.japangirelay.relay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangirelay.controller.dto.RelayRequest;
import io.github.japangirelay.socketclient.SocketClient;
import org.springframework.stereotype.Component;

@Component
public class RelayManager {

    private static final String PING_SIGNAL = "PING";

    private final SocketClient socketClient;
    private final ObjectMapper objectMapper;

    public RelayManager(SocketClient socketClient, ObjectMapper objectMapper) {
        this.socketClient = socketClient;
        this.objectMapper = objectMapper;
    }

    public String relay(RelayRequest request) {
        String serializedRequest = serialize(request);

        socketClient.writeMassage(serializedRequest);
        while (true) {
            String response = socketClient.readMassage();

            if (!response.equals(PING_SIGNAL)) {
                return response;
            }
        }
    }

    public String serialize(RelayRequest request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
