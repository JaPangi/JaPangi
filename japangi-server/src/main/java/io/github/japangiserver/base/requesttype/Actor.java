package io.github.japangiserver.base.requesttype;

import io.github.japangiserver.admin.requesttype.AdminRequestType;
import io.github.japangiserver.base.error.BusinessException;
import io.github.japangiserver.base.error.GlobalErrorCode;
import io.github.japangiserver.product.requesttype.ProductRequestType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Actor {

    PRODUCT("PRODUCT_", RequestTypeBinarySearchTree.of(ProductRequestType.values())),
    ADMIN("ADMIN_", RequestTypeBinarySearchTree.of(AdminRequestType.values()));

    private final String prefix;
    private final RequestTypeBinarySearchTree requestTypeBinarySearchTree;

    public static RequestType getMatchedType(String typeName) {
        Actor actor = Arrays.stream(values())
                .filter(it -> typeName.startsWith(it.prefix))
                .findFirst()
                .orElseThrow(() -> new BusinessException(GlobalErrorCode.INVALID_REQUEST_TYPE_PREFIX));

        return actor.requestTypeBinarySearchTree.search(typeName);
    }
}
