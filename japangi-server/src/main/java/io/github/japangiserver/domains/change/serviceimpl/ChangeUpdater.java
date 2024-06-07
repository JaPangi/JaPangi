package io.github.japangiserver.domains.change.serviceimpl;


import io.github.japangiserver.domains.change.ChangeEntity;
import io.github.japangiserver.domains.money.MoneyEntity;
import io.github.japangiserver.domains.money.serviceimpl.MoneyReader;
import io.github.japangiserver.domains.order.domain.MoneyAmount;
import io.github.japangiserver.domains.order.domain.MoneyAmounts;
import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.serviceimpl.VendingMachineReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChangeUpdater {

    private final ChangeReader changeReader;
    private final MoneyReader moneyReader;
    private final VendingMachineReader vendingMachineReader;

    @Transactional
    public void insertChange(Long vendingMachineId, MoneyAmounts moneyAmounts) {
        List<MoneyAmount> amounts = moneyAmounts.moneyAmounts();
        VendingMachineEntity vendingMachineEntity = vendingMachineReader.getVendingMachine(vendingMachineId);
        for (MoneyAmount moneyAmount : amounts) {
            MoneyEntity moneyEntity = moneyReader.getMoney(moneyAmount.value());
            ChangeEntity changeEntity = changeReader.getChangeEntity(vendingMachineEntity, moneyEntity);
            changeEntity.increaseAmount(moneyAmount.amount());
        }
    }

    public void collectChange(Long vendingMachineId) {
        changeReader.getChange(vendingMachineId)
            .stream().
            filter(change -> change.getAmount() > 5)
            .forEach(ChangeEntity::collectMoneyByAdmin);
    }
}
