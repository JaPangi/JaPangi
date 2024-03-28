package io.github.japangiserver.product.order.dto.request;

import io.github.japangiserver.domain.order.MoneyAmount;
import io.github.japangiserver.domain.order.OrderTarget;
import io.github.japangiserver.product.money.dto.request.MoneyRequest;

import java.util.List;

public record OrderRequest(
        Long drinkId,
        Long vendingMachineId,
        List<MoneyRequest> monies
) {

    public List<MoneyAmount> toMoneyAmounts() {
        return monies.stream()
                .map(MoneyRequest::toMoneyAmount)
                .toList();
    }

    public OrderTarget toTarget(){
        return new OrderTarget(drinkId,vendingMachineId);
    }
}
