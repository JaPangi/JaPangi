package io.github.japangiserver.domains.order;

import java.util.List;

/** NOTE
 * 화폐 단위 및 양 List domain
 */
public record MoneyAmounts(
    List<MoneyAmount> moneyAmounts
) {

    /** NOTE
     * 클라이언트가 낸 금액의 총합 계산
     */
    public int calculateTotalPrice(){
        return moneyAmounts.stream()
            .mapToInt(MoneyAmount::calculatePrice)
            .sum();
    }
}
