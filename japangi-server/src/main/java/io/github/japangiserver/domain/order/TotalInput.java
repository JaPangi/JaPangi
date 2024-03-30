package io.github.japangiserver.domain.order;

import java.util.AbstractMap;
import java.util.List;

public class TotalInput {
    private List<MoneyAmount> moneyAmounts;

    public TotalInput(List<MoneyAmount> moneyAmounts) {
        this.moneyAmounts = moneyAmounts;
    }

    public int totalPrice(List<MoneyAmount> amounts){
        return moneyAmounts.stream()
                .mapToInt(moneyAmount -> moneyAmount.value() * moneyAmount.amount())
                .sum();
    }

}
