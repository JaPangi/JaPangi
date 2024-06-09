package io.github.japangiserver.domains.stock.service;

import io.github.japangiserver.domains.stock.AddStock;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockReader;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockUpdater stockUpdater;
    private final StockReader stockReader;

    @Transactional
    public Long putStock(AddStock addStock) {
        AddStock stock = stockReader.getStock(addStock.drinkId(), addStock.vendingMachineId());
        stockUpdater.updateAddAmount(stock);
        return addStock.vendingMachineId();
    }
}
