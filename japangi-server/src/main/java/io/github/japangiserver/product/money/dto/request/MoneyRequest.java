package io.github.japangiserver.product.money.dto.request;

import io.github.japangiserver.domain.order.MoneyAmount;

public record MoneyRequest(
        Integer value,
        int amount
) {

    public MoneyAmount toMoneyAmount() {
        return new MoneyAmount(value, amount);
    }
}
