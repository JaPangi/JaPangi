package io.github.japangiserver.domains.order.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    public void checkInputMoney(int drinkPrice, int inputMoney){
        if(drinkPrice > inputMoney){
            throw new IllegalStateException("넣은 돈이 부족합니다!");

        }
    }
}
