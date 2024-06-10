package io.github.japangiserver.domains.money.serviceimpl;

import io.github.japangiserver.domains.order.MoneyAmount;
import org.springframework.stereotype.Component;

/** NOTE
 * MoneyAmount domain 생성 implement Layer
 */
@Component
public class MoneyAmountCreator {

    public MoneyAmount getMoneyAmount(int changeAmount, int value) {
        return MoneyAmount.builder()
            .amount(changeAmount)
            .value(value)
            .build();
    }
}
