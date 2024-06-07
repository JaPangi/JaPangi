package io.github.japangiserver.domains.order.dto.request;

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
}
