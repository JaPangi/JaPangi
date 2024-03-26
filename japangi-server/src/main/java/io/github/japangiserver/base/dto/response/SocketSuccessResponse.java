package io.github.japangiserver.base.dto.response;

import lombok.Getter;

@Getter
public class SocketSuccessResponse <T> extends SocketResponse {

    private final T data;

    public SocketSuccessResponse(ResponseStatus status, T data) {
        super(status);
        this.data = data;
    }
}
