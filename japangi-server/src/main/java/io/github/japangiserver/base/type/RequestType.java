package io.github.japangiserver.base.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RequestType {

    EXAMPLE("example", "exampleUseCase");

    private final String name;
    private final String useCaseName;

    public static String provideUseCase(String name) {
        RequestType type = Arrays.stream(values())
                .filter(it -> it.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 요청 타입 입니다."));

        return type.useCaseName;
    }
}
