package io.github.japangiserver.domains.stock;


/** NOTE
 * stock domain
 * @param drinkId 음료 Id(PK)
 * @param amount 음료 수량
 * @param vendingMachineId 자판기 Id(PK)
 */
public record AddStock(long drinkId, int amount, long vendingMachineId) {

}
