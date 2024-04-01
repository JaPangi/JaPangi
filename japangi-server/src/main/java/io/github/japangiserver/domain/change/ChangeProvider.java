package io.github.japangiserver.domain.change;

import io.github.japangiserver.domain.order.MoneyAmount;
import io.github.japangiserver.domain.order.MoneyAmounts;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Component
public class ChangeProvider {

    /**
     * 거스름돈을 거슬러 주는 메소드
     *
     * @param excess  거슬러줘야 하는 금액
     * @param changes 자판기가 보유하고 있는 거스름돈의 목록
     * @return 거스름돈
     */
    public MoneyAmounts provide(int excess, List<Change> changes) {
        changes.sort(Comparator.comparing(change -> change.getMoney().getMoneyId()));

        AtomicInteger remainingChanges = new AtomicInteger(excess);
        List<MoneyAmount> moneyAmounts = new ArrayList<>();
        IntStream.range(0, changes.size())
            .map(i -> changes.size() - i-1)
            .forEach(i -> {
                int value = changes.get(i).getMoney().getValue();
                int changeAmount = remainingChanges.get() / value;
                changes.get(i).validChanges(changeAmount); //자판기가 거스러줄 잔돈이 있는지 확인
                if (changeAmount > 0) {
                    remainingChanges.addAndGet(-changeAmount * value);
                    MoneyAmount amount = MoneyAmount.builder()
                        .amount(changeAmount)
                        .value(value)
                        .build();
                    moneyAmounts.add(amount);
                    changes.get(i).remainChange(changeAmount);
                }
            });
        return new MoneyAmounts(moneyAmounts);
    }
}
