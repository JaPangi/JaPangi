package io.github.japangiserver.base.dto.response;

import io.github.japangiserver.domain.order.MoneyAmounts;

public record MoneyAmountResponse(
    MoneyAmounts moneyAmounts
) {

}
