package io.github.japangiserver.exceptionhandler;

import io.wwan13.dispatchersorvlet.sorvlet.annotation.ExceptionHandler;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;

/** NOTE
 * exception 처리 구현
 */
public class SocketExceptionHandler {
    @ExceptionHandler(support = IllegalStateException.class)
    public SocketResponse handleIllegalStateException(IllegalStateException e) {

        // some exception handling logics

        return SocketResponse.error("IllegalStateException", e.getMessage());
    }

    @ExceptionHandler(support = IllegalArgumentException.class)
    public SocketResponse handleIllegalArgumentException(IllegalArgumentException e){
        return SocketResponse.error("IllegalArgumentException",e.getMessage());

    }

    @ExceptionHandler(support = Exception.class)
    public SocketResponse handleDefaultException(Exception e) {

        // some exception handling logics

        return SocketResponse.error("Exception", e.getMessage());
    }
}