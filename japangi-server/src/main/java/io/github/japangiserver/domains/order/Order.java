package io.github.japangiserver.domains.order;

/** NOTE
 * 상위 도메인
 */
public record Order(
    int drinkPrice,
    String drinkName,
    OrderTarget orderTarget,
    MoneyAmounts moneyAmounts
) {

}
