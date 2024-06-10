package io.github.japangiserver.domains.stock.service;

import io.github.japangiserver.domains.stock.AddStock;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockReader;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 재고 핵심 비즈니스 로직 service Layer
 */
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockUpdater stockUpdater;
    private final StockReader stockReader;

    /** NOTE
     * 음료 재고 충전 서비스
     * @param addStock 음료 Id, 충전할 재고 양, 자판기 Id
     */
    @Transactional
    public Long putStock(AddStock addStock) {
        AddStock stock = stockReader.getStock(addStock.drinkId(), addStock.vendingMachineId());
        stockUpdater.updateAddAmount(stock);
        return addStock.vendingMachineId();
    }
}
