package io.github.japangiserver.base.usecase;

import io.github.japangiserver.base.dto.request.SocketRequest;
import io.github.japangiserver.base.dto.response.SocketResponse;
import io.github.japangiserver.base.requesttype.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UseCaseExecutor {

    private final Map<String, BaseUseCase> useCaseMap;

    public SocketResponse execute(SocketRequest request) {
        return useCaseMap.get(Actor.getMatchedType(request.type()).getUseCaseName())
                .execute(request.data());
    }
}
