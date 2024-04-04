package io.github.japangiserver.base.requesttype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SystemRequestType implements RequestType {

    SYSTEM_EXIT("SYSTEM_EXIT", "");

    private final String name;
    private final String useCaseName;
}
