package io.github.japangiserver.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.dto.request.SocketRequest;
import io.github.japangiserver.base.dto.response.SocketResponse;
import io.github.japangiserver.base.error.BusinessException;
import io.github.japangiserver.base.usecase.BaseUseCase;
import io.github.japangiserver.base.usecase.UseCaseProvider;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

@Getter
@Slf4j
public class SocketMessageHandler extends AbstractSocketHandler {

    private final UseCaseProvider useCaseProvider;
    private final ObjectMapper objectMapper;

    public SocketMessageHandler(
            Socket client,
            UseCaseProvider useCaseProvider,
            ObjectMapper objectMapper
    ) throws IOException {
        super(client);
        this.useCaseProvider = useCaseProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    void afterConnectionEstablished() throws IOException {
        log.info("{} has joined server", connection.getRemoteSocketAddress().toString());
    }

    @Override
    public void handleSocketMessage(String message) throws IOException {
        if (isExit(message)) {
            quit();
            return;
        }
        executeAndWriteResponse(() -> {
            SocketRequest request = SocketRequest.deserializeFrom(message, objectMapper);
            BaseUseCase useCase = useCaseProvider.provide(request.type());
            return useCase.execute(request.data());
        });
    }

    private void executeAndWriteResponse(Supplier<SocketResponse> action) throws IOException {
        SocketResponse response = null;
        try {
            response = action.get();
        } catch (BusinessException e) {
            log.error(e.getErrorMessage());
            response = SocketResponse.error(e);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            response = SocketResponse.error(e.getMessage());
        } finally {
            write(response.serialize(objectMapper));
        }
    }

    private boolean isExit(String message) {
        return message.contains("SYSTEM_EXIT");
    }

    @Override
    void afterConnectionClosed() throws IOException {
        log.info("{} has left server", connection.getRemoteSocketAddress().toString());
    }
}
