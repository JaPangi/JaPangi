package io.github.japangiserver.base.dto.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.error.BusinessException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SocketResponse {

    private final ResponseStatus status;

    public enum ResponseStatus {
        SUCCESS, ERROR;
    }

    public static <T> SocketSuccessResponse success(T data) {
        return new SocketSuccessResponse<>(ResponseStatus.SUCCESS, data);
    }

    public static SocketErrorResponse error(BusinessException e) {
        return new SocketErrorResponse(ResponseStatus.ERROR, e.getErrorCode(), e.getErrorMessage());
    }

    public static SocketErrorResponse error(String message) {
        return new SocketErrorResponse(ResponseStatus.ERROR, "GLOBAL_500", message);
    }

    public String serialize(ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("cannot serialize Response");
        }
    }
}
