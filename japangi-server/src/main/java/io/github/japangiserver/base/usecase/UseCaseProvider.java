package io.github.japangiserver.base.usecase;

import io.github.japangiserver.base.requesttype.Actor;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UseCaseProvider {

    private final Map<String, BaseUseCase> useCaseMap;

    public BaseUseCase provide(String type) {
        return useCaseMap.get(Actor.getMatchedType(type).getUseCaseName());
    }
}
