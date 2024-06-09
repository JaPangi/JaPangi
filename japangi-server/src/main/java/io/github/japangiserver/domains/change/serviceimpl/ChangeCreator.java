package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import io.github.japangiserver.domains.money.persistence.entity.MoneyEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.repository.VendingMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ChangeCreator {
    public final static int DEFAULT_AMOUNT = 10;
    private final VendingMachineRepository vendingMachineRepository;

    public ChangeEntity createChange(
        Long vendingMachineId,
        MoneyEntity money
    ) {
        VendingMachineEntity vendingMachineEntity = vendingMachineRepository.findById(
                vendingMachineId
            )
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 자판기입니다!"));

        return ChangeEntity.builder()
            .vendingMachineEntity(vendingMachineEntity)
            .amount(DEFAULT_AMOUNT)
            .moneyEntity(money)
            .build();
    }
}
