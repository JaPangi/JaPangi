package io.github.japangiserver.base.usecase;

import io.github.japangiserver.base.type.RequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UseCaseProvider {

    private final Map<String, BaseUseCase> useCaseMap;

    public BaseUseCase provide(String type) {
        return useCaseMap.get(RequestType.provideUseCase(type));
    }
}
