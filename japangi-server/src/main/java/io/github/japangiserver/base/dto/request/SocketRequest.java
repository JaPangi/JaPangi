package io.github.japangiserver.base.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record SocketRequest(
        String type,
        Object data
) {

    public static SocketRequest deserializeFrom(String data, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(data, SocketRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("cannot deserialize Request");
        }
    }
}
