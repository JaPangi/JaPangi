package io.github.japangiserver.domains.change.serviceimpl;


import io.github.japangiserver.domains.change.Change;
import io.github.japangiserver.domains.change.persistence.ChangeEntityReader;
import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import io.github.japangiserver.domains.money.serviceimpl.MoneyReader;
import io.github.japangiserver.domains.order.MoneyAmount;
import io.github.japangiserver.domains.order.MoneyAmounts;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChangeUpdater {

    private final ChangeReader changeReader;
    private final MoneyReader moneyReader;
    private final ChangeEntityReader changeEntityReader;

    @Transactional
    public void insertChange(Long vendingMachineId, MoneyAmounts moneyAmounts) {
        List<MoneyAmount> amounts = moneyAmounts.moneyAmounts();
        for (MoneyAmount moneyAmount : amounts) {
            Long moneyId = moneyReader.getMoney(moneyAmount.value());
            Change change = changeReader.getChange(vendingMachineId, moneyId);

            ChangeEntity changeEntity = changeEntityReader.getChangeEntity(change);
            changeEntity.increaseAmount(moneyAmount.amount());
        }
    }

    @Transactional
    public void collectChange(Long vendingMachineId) {

        changeReader.getChanges(vendingMachineId)
            .stream()
            .filter(change -> change.amount() > 5)
            .forEach(change -> {
                ChangeEntity changeEntity = changeEntityReader.getChangeEntity(change);
                changeEntity.collectMoneyByAdmin();
            });
    }
}
