package io.github.japangiserver.domains.order;

import lombok.Builder;

/** NOTE
 * money domain
 * @param value 화폐 단위
 * @param amount 화폐 갯수(장)
 */
@Builder
public record MoneyAmount(
    int value,
    int amount
) {

    public int calculatePrice() {
        return value * amount;
    }
}
