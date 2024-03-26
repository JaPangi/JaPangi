package io.github.japangiserver.product.requesttype;

import io.github.japangiserver.base.requesttype.RequestType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductRequestType implements RequestType {

    EXAMPLE("PRODUCT_EXAMPLE", "exampleUseCase");

    private final String name;
    private final String useCaseName;
}
