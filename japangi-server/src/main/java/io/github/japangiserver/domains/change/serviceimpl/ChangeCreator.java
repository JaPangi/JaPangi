package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.ChangeEntity;
import io.github.japangiserver.domains.money.MoneyEntity;
import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import org.springframework.stereotype.Component;


@Component
public class ChangeCreator {
    public final static int DEFAULT_AMOUNT = 10;

    public ChangeEntity createChange(
        VendingMachineEntity vendingMachine,
        MoneyEntity money
    ) {
        return ChangeEntity.builder()
            .vendingMachineEntity(vendingMachine)
            .amount(DEFAULT_AMOUNT)
            .moneyEntity(money)
            .build();
    }
}
