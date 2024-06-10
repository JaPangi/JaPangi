package io.github.japangiserver.domains.stock.service.serviceimpl;

import org.springframework.stereotype.Component;

/** NOTE
 * 재고 유효성 검증 implement Layer
 */
@Component
public class StockValidator {

    /** NOTE
     * 남은 재고 확인 구현체
     * @param amount 재고 양
     */
    public void validStock(int amount){
        if(amount < 1){
            throw new IllegalStateException("재고가 부족합나다!");
        }
    }
}
