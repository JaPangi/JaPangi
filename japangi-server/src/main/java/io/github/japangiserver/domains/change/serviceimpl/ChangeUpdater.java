package io.github.japangiserver.domains.change.serviceimpl;


import io.github.japangiserver.domains.change.Change;
import io.github.japangiserver.domains.change.persistence.ChangeEntityReader;
import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import io.github.japangiserver.domains.money.serviceimpl.MoneyReader;
import io.github.japangiserver.domains.order.MoneyAmount;
import io.github.japangiserver.domains.order.MoneyAmounts;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 거스름돈 업데이트 implement Layer
 */
@Component
@RequiredArgsConstructor
public class ChangeUpdater {

    private final ChangeReader changeReader;
    private final MoneyReader moneyReader;
    private final ChangeEntityReader changeEntityReader;

    /** NOTE
     * 거스름돈 추가 구현체
     * @param vendingMachineId 추가할 자판기 Id(PK)
     * @param moneyAmounts 추가할 화폐 양
     */
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

    /** NOTE
     * 자판기 별 거스름돈 수거 구현체
     * @param vendingMachineId 수거할 자판기 Id(PK)
     */
    @Transactional
    public int collectChange(Long vendingMachineId) {
       return changeReader.getChanges(vendingMachineId)
            .stream()
            .filter(change -> change.amount() > 5)
            .map(change -> {
                ChangeEntity changeEntity = changeEntityReader.getChangeEntity(change);
                int money = changeEntity.collectMoneyByAdmin();
                return change.value()* money;
            })
            .reduce(0,Integer::sum);
    }
}
