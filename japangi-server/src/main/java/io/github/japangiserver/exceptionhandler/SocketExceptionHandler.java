package io.github.japangiserver.exceptionhandler;

import io.wwan13.dispatchersorvlet.sorvlet.annotation.ExceptionHandler;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;

public class SocketExceptionHandler {
    @ExceptionHandler(support = IllegalStateException.class)
    public SocketResponse handleIllegalStateException(IllegalStateException e) {

        // some exception handling logics

        return SocketResponse.error("IllegalStateException", e.getMessage());
    }

    @ExceptionHandler(support = Exception.class)
    public SocketResponse handleDefaultException(Exception e) {

        // some exception handling logics

        return SocketResponse.error("Exception", e.getMessage());
    }
}