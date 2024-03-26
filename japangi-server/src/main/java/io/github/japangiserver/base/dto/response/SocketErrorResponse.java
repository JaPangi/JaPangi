package io.github.japangiserver.base.dto.response;

import lombok.Getter;

@Getter
public class SocketErrorResponse extends SocketResponse {

    private final String code;
    private final String message;

    public SocketErrorResponse(ResponseStatus status, String code, String message) {
        super(status);
        this.code = code;
        this.message = message;
    }
}
