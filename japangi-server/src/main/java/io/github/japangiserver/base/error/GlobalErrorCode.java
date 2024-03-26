package io.github.japangiserver.base.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    CANNOT_DESERIALIZE("GLOBAL_500_1", "값을 역직렬화 할 수 없습니다."),
    CANNOT_SERIALIZE("GLOBAL_500_2", "값을 읽을 수 없습니다.");

    private final String errorCode;
    private final String errorMessage;
}
