package io.github.japangiserver.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.dto.request.SocketRequest;
import io.github.japangiserver.base.dto.response.SocketResponse;
import io.github.japangiserver.base.error.BusinessException;
import io.github.japangiserver.base.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class SocketMessageHandler {

    private final UseCaseExecutor useCaseExecutor;
    private final ObjectMapper objectMapper;

    void handleSocketMessage(String message, SocketConnection connection) {
        executeAndWriteResponse(connection, () -> {
            SocketRequest request = SocketRequest.deserializeFrom(message, objectMapper);
            return useCaseExecutor.execute(request);
        });
    }

    private void executeAndWriteResponse(
            SocketConnection connection,
            Supplier<SocketResponse> action
    ) {
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
            connection.write(response.serialize(objectMapper));
        }
    }
}
