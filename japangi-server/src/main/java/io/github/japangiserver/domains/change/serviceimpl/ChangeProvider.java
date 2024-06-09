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

@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeProvider {

    private final ChangeReader changeReader;
    private final ChangeValidator changeValidator;
    private final ChangeCalculator changeCalculator;
    private final MoneyAmountCreator moneyAmountCreator;

    /**
     * 거스름돈을 거슬러 주는 메소드
     * @param excess 거슬러줘야 하는 금액
     * @return 거스름돈
     */
    @Transactional
    public MoneyAmounts provide(int excess, Long vendingMachineId) { //2450 13
        List<MoneyAmount> moneyAmount = new ArrayList<>();
        List<Change> change = changeReader.getChanges(vendingMachineId);
        for (Change changeDomain : change) {
            int value = changeReader.getValue(changeDomain); //1000원
            int changeAmount = excess / value; //6700 / 5000 = 2
            changeValidator.checkChanges(changeDomain, changeAmount); //잔돈이 자판기에 남아있는지 확인

            if (changeAmount > 0) {
                excess = changeCalculator.calculatorChange(changeDomain, changeAmount, excess, value);
                MoneyAmount amount = moneyAmountCreator.getMoneyAmount(changeAmount, value);
                moneyAmount.add(amount);
            }
        }
        return new MoneyAmounts(moneyAmount);
    }
}
