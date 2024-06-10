package io.github.japangiserver.domains.order;

/** NOTE
 * 주문 domain
 * @param drinkId 음료 Id(PK)
 * @param vendingMachineId 자판기 Id(PK)
 */
public record OrderTarget(long drinkId, long vendingMachineId) {

}
