package io.github.japangiserver.presentation.order.dto.request;

import io.github.japangiserver.domains.order.MoneyAmount;
import io.github.japangiserver.domains.order.MoneyAmounts;
import io.github.japangiserver.domains.order.OrderTarget;
import java.util.List;

public record OrderRequest(
    long drinkId,
    long vendingMachineId,
    List<MoneyRequest> moneyAmounts

) {

    public record MoneyRequest(
        int value,
        int amount
    ) {

    }

    public OrderTarget toOrderTarget() {
        return new OrderTarget(drinkId, vendingMachineId);
    }

    public MoneyAmounts toMoneyAmounts() {
        return new MoneyAmounts(moneyAmounts.stream()
            .map(e -> new MoneyAmount(
                    e.value(),
                    e.amount()
                )
            )
            .toList()
        );
    }
}
