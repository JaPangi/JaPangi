package io.github.japangiserver.product.requesttype;

import io.github.japangiserver.base.requesttype.RequestType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductRequestType implements RequestType {

    EXAMPLE("PRODUCT_EXAMPLE","exampleUseCase"),
    ADD_ITEM("PRODUCT_ADD_ITEM","addItemUseCase"),
    PURCHASE_ITEM("PRODUCT_PURCHASE_ITEM","purchaseUseCase");

    private final String name;
    private final String useCaseName;
}
