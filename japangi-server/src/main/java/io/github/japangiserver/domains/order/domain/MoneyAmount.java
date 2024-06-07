package io.github.japangiserver.domains.order.domain;

import lombok.Builder;

@Builder
public record MoneyAmount(
    int value, // 얼마인지
    int amount // 몇개(장)인지
) {

    public int calculatePrice() {
        return value * amount;
    }
}
