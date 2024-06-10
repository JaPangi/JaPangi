package io.github.japangiserver.domains.order.service.serviceimpl;

import org.springframework.stereotype.Component;

/** NOTE
 * 거스름돈 계산하는 implement Layer
 */
@Component
public class OrderCalculator {

    /** NOTE
     * 거스름돈 계산하는 구현체
     * @param totalPrice 클라이언트가 투입한 금액
     * @param drinkPrice 음료 금액
     */
    public int calculateChanges(int totalPrice, int drinkPrice){
        return totalPrice - drinkPrice;
    }
}
