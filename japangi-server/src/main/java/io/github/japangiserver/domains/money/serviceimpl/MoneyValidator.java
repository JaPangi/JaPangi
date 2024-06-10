package io.github.japangiserver.domains.money.serviceimpl;

import static io.github.japangiserver.domains.money.serviceimpl.MoneySaver.moneyTypeMap;

import io.github.japangiserver.domains.money.persistence.entity.MoneyType;
import io.github.japangiserver.domains.order.MoneyAmount;
import io.github.japangiserver.domains.order.MoneyAmounts;
import org.springframework.stereotype.Component;

/** NOTE
 * 화폐 유효성 검증 implement Layer
 */
@Component
public class MoneyValidator {

    /** NOTE
     * 5000원 이상의 지폐 단위 검증 구현체
     * @param moneyAmounts 클라이언트가 넣은 화폐 양
     */
    public void checkBillCount(MoneyAmounts moneyAmounts) {
        int sum = moneyAmounts.moneyAmounts().stream()
            .filter(MoneyValidator::isBills)
            .mapToInt(MoneyAmount::amount)
            .sum();

        if (sum > 5) {
            throw new IllegalArgumentException("한번에 5000원 이상의 지폐는 안됩니다!");
        }
    }

    /** NOTE
     * 지폐인지 확인하는 구현체
     */
    private static boolean isBills(MoneyAmount moneyAmount) {
        return moneyTypeMap.get(moneyAmount.value()).equals(MoneyType.BILL);
    }

    /** NOTE
     * 클라이언트가 투입한 전체 화폐 금액 확인 구현체
     */
    public int checkTotalCount(MoneyAmounts moneyAmounts) {
        if (moneyAmounts.calculateTotalPrice() > 7000) {
            throw new IllegalArgumentException("7000원 이상 금액을 넣을 수 없습니다!");
        }
        return moneyAmounts.calculateTotalPrice();
    }
}
