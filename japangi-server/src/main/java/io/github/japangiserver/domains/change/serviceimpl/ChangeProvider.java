package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.Change;
import io.github.japangiserver.domains.money.serviceimpl.MoneyAmountCreator;
import io.github.japangiserver.domains.order.MoneyAmount;
import io.github.japangiserver.domains.order.MoneyAmounts;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 거스름돈 제공 implement Layer
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeProvider {

    private final ChangeReader changeReader;
    private final ChangeValidator changeValidator;
    private final ChangeCalculator changeCalculator;
    private final MoneyAmountCreator moneyAmountCreator;

    /** NOTE
     * 거스름돈을 거슬러 주는 구현체
     * @param excess 거슬러줘야 하는 금액
     * @return 거스름돈
     */
    @Transactional
    public MoneyAmounts provide(int excess, Long vendingMachineId) {
        List<MoneyAmount> moneyAmount = new ArrayList<>();
        List<Change> change = changeReader.getChanges(vendingMachineId);
        for (Change changeDomain : change) {
            int value = changeReader.getValue(changeDomain); //화폐 단위
            System.out.println("value = " + value);
            int changeAmount = excess / value; //거스러줘야할 화폐의 양
            System.out.println("changeAmount = " + changeAmount);
            boolean checked = changeValidator.checkChanges(changeDomain, changeAmount);//잔돈이 자판기에 남아있는지 확인

            if (changeAmount > 0 && checked) {
                excess = changeCalculator.calculatorChange(changeDomain, changeAmount, excess, value);
                MoneyAmount amount = moneyAmountCreator.getMoneyAmount(changeAmount, value);
                moneyAmount.add(amount);
            }
        }
        return new MoneyAmounts(moneyAmount);
    }
}
