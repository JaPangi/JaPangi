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

    public MoneyAmounts provide(int changes, //거슬러 줘여할 돈
                                     List<Change> changeList) { //지판기에 있는 거스름돈 현황

        changeList.sort(Comparator.comparing(change -> change.getMoney().getMoneyId())); //1 2 3 4 5
        AtomicInteger remainingChanges = new AtomicInteger(changes);
        List<MoneyAmount> moneyAmounts=new ArrayList<>();

        IntStream.range(changeList.size(), 0)
                .forEach(i -> {
                    int value = changeList.get(i).getMoney().getValue();
                    int changeAmount = remainingChanges.get() / value;

                    if (changeAmount > 0) {
                        remainingChanges.addAndGet(-changeAmount * value);
                        MoneyAmount amount = MoneyAmount.builder()
                                .amount(changeAmount)
                                .value(value)
                                .build();
                        moneyAmounts.add(amount);
                    }
                });
       return new MoneyAmounts(moneyAmounts);
    }
}
