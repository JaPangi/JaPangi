package io.github.japangiserver.domains.order.mapper;

import io.github.japangiserver.domains.order.domain.MoneyAmount;
import io.github.japangiserver.domains.order.domain.MoneyAmounts;
import io.github.japangiserver.domains.order.domain.OrderTarget;
import io.github.japangiserver.domains.order.dto.request.OrderRequest.MoneyRequest;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderTargetMapper {
    public OrderTarget toOrderTarget(long drinkId, long vendingMachineId){
        return new OrderTarget(drinkId,vendingMachineId);
    }

    public MoneyAmounts toMoneyAmounts(List<MoneyRequest> moneyAmounts){
        return new MoneyAmounts(
            moneyAmounts.stream()
                .map(e -> new MoneyAmount(
                    e.value(),
                    e.amount()
                ))
                .toList()
        );
    }
}
