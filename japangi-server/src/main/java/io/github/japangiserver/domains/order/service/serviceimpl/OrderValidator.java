package io.github.japangiserver.domains.order.service.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/** NOTE
 * 주문 유효성 검증 implement Layer
 */
@Component
@RequiredArgsConstructor
public class OrderValidator {

    /** NOTE
     * 클라이언트가 투입한 금액의 합 검사 구현체
     * @param drinkPrice 음료 가격
     * @param inputMoney 클라이언트가 넣은 돈
     */
    public void checkInputMoney(int drinkPrice, int inputMoney){
        if(drinkPrice > inputMoney){
            throw new IllegalStateException("넣은 돈이 부족합니다!");
        }
    }
}
