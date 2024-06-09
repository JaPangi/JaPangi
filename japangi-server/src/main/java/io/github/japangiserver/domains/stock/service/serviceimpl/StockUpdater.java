package io.github.japangiserver.domains.stock.service.serviceimpl;

import io.github.japangiserver.domains.stock.AddStock;
import io.github.japangiserver.domains.stock.persistence.StockEntityReader;
import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
@RequiredArgsConstructor
public class StockUpdater {
    private final StockEntityReader stockEntityReader;
    @Transactional
    public void updateRemoveAmount(AddStock stock){
        StockEntity stockEntity = stockEntityReader.getStockEntity(stock);
        stockEntity.removeAmount();
    }

    @Transactional
    public void updateAddAmount(AddStock addStock) {
        StockEntity stockEntity = stockEntityReader.getStockEntity(addStock);
        stockEntity.increaseAmount(addStock.amount());
    }

}
