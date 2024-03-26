package io.github.japangiserver.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.dto.request.SocketRequest;
import io.github.japangiserver.base.dto.response.SocketResponse;
import io.github.japangiserver.base.error.BusinessException;
import io.github.japangiserver.base.usecase.BaseUseCase;
import io.github.japangiserver.base.usecase.UseCaseProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final UseCaseProvider useCaseProvider;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} has join server", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        SocketResponse response = executeWithExceptionHandle(payload);

        sendResponse(session, response);
    }

    private SocketResponse executeWithExceptionHandle(String payload) {
        try {
            SocketRequest request = objectMapper.readValue(payload, SocketRequest.class);
            BaseUseCase useCase = useCaseProvider.provide(request.type());
            return useCase.execute(request.data());
        } catch (BusinessException e) {
            log.error(e.getErrorMessage());
            return SocketResponse.error(e);
        } catch (JsonProcessingException | RuntimeException e) {
            log.error(e.getMessage());
            return SocketResponse.error(e.getMessage());
        }
    }

    private void sendResponse(WebSocketSession session, SocketResponse response) {
        try {
            String serializedResponse = objectMapper.writeValueAsString(response);
            session.sendMessage(new TextMessage(serializedResponse));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("유효하지 않은 데이터 입니다.");
        } catch (IOException e) {
            throw new RuntimeException("메세지 전송에 실패하였습니다.");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} has left server", session.getId());
    }
}
