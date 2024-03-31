package io.github.japangiserver.domain.order;

import java.util.List;

public record MoneyAmounts(
    List<MoneyAmount> moneyAmounts
) {
    public int calculateTotalPrice(){
        return moneyAmounts.stream()
            .mapToInt(MoneyAmount::calculatePrice)
            .sum();
    }
}
