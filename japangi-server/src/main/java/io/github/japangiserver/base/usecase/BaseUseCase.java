package io.github.japangiserver.base.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseUseCase<T> {

    private final ObjectMapper objectMapper;

    public SocketResponse execute(Object data) {
        return core(casting(data));
    }

    private T casting(Object data) {
        return objectMapper.convertValue(data, support());
    }

    abstract public SocketResponse core(T data);

    abstract public Class<T> support();
}
