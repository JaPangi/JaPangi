package io.github.japangiserver.domains.stock.application.serviceimpl;

import org.springframework.stereotype.Component;

@Component
public class StockValidator {
    public void validStock(int amount){
        if(amount < 1){
            throw new IllegalStateException("재고가 부족합나다!");
        }
    }
}
