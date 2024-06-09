package io.github.japangiserver.domains.change;

import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import lombok.Builder;

@Builder
public record Change(
    long changeId,
    int amount,
    int value,
    long vendingMachineId,
    long moneyId
) {
    public static Change toChange(ChangeEntity changeEntity){
        return Change.builder()
            .changeId(changeEntity.getChangeId())
            .amount(changeEntity.getAmount())
            .value(changeEntity.getMoneyEntity().getValue())
            .vendingMachineId(changeEntity.getVendingMachineEntity().getVendingMachineId())
            .moneyId(changeEntity.getMoneyEntity().getMoneyId())
            .build();
    }

}
