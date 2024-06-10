package io.github.japangiserver.domains.change;

import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import lombok.Builder;

/** NOTE
 *  Change domain
 * @param changeId 거스름돈 Id(PK)
 * @param amount 화폐 양
 * @param value 화폐 값
 * @param vendingMachineId 자판기 Id(PK)
 * @param moneyId 화폐 Id(PK)
 */
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
