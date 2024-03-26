package io.github.japangiserver.base.dto.request;

public record SocketRequest(
        String type,
        Object data
) {
}
