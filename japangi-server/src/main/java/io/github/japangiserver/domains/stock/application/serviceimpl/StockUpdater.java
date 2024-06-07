package io.github.japangiserver.domains.stock.application.serviceimpl;

import io.github.japangiserver.domains.stock.adapter.persistence.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockUpdater {

    private final StockReader stockReader;
    public void updateRemoveAmount(StockEntity stockEntity){
        stockEntity.removeAmount();
    }

    public void updateAddAmount(Long stockId, int amount){
        stockReader.findStock(stockId).increaseAmount(amount);
    }
}
