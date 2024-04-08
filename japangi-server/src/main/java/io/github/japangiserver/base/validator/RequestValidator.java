package io.github.japangiserver.base.validator;

import io.github.japangiserver.base.error.BusinessException;
import io.github.japangiserver.base.error.GlobalErrorCode;
import org.springframework.util.StringUtils;

public class RequestValidator {

    private RequestValidator() {
    }

    public static void notNull(String data) {
        if (!StringUtils.hasText(data)) {
            throw new BusinessException(GlobalErrorCode.INVALID_INPUT_DATA);
        }
    }

    public static void notNull(Object data) {

    }

    public static void isGreaterThan(Number data) {

    }

    public static void isLessThan(Number data) {

    }
}
