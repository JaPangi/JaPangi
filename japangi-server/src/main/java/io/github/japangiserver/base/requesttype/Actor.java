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

    SYSTEM("SYSTEM_", List.of(SystemRequestType.values())),
    PRODUCT("PRODUCT_", List.of(ProductRequestType.values())),
    ADMIN("ADMIN_", List.of(AdminRequestType.values()));

    private final String prefix;
    private final List<RequestType> types;

    public static RequestType getMatchedType(String typeName) {
        Actor actor = Arrays.stream(values())
                .filter(it -> typeName.startsWith(it.prefix))
                .findFirst()
                .orElseThrow(() -> new BusinessException(GlobalErrorCode.INVALID_REQUEST_TYPE_PREFIX));

        return actor.getTypes().stream()
                .filter(it -> it.getName().equals(typeName))
                .findFirst()
                .orElseThrow(() -> new BusinessException(GlobalErrorCode.REQUEST_TYPE_NOT_FOUND));
    }
}
