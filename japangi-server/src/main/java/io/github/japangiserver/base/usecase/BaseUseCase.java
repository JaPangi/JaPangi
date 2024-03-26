package io.github.japangiserver.base.usecase;

import io.github.japangiserver.base.dto.response.SocketResponse;

public abstract class BaseUseCase<T> {

    public SocketResponse execute(Object data) {
        return core(casting(data));
    }

    private T casting(Object data) {
        return (T) data;
    }

    abstract SocketResponse core(T data);
}
