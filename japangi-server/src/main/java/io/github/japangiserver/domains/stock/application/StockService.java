package io.github.japangiserver.domains.stock.application;

import io.github.japangiserver.domains.admin.dto.request.AdminStockRequest;
import io.github.japangiserver.domains.stock.application.dto.response.StockResponse;
import io.github.japangiserver.domains.stock.application.serviceimpl.StockReader;
import io.github.japangiserver.domains.stock.application.serviceimpl.StockUpdater;
import io.github.japangiserver.domains.stock.domain.AddStock;
import io.github.japangiserver.domains.stock.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockReader stockReader;
    private final StockUpdater stockUpdater;
    private final StockMapper stockMapper;

    @Transactional
    public Long putStock(Long vendingMachineId, AdminStockRequest addStock) {
        AddStock stock = stockMapper.toAddStock(addStock);
        StockResponse response = stockReader.getStockResponse(
            stock.drinkId(), vendingMachineId
        );
        stockUpdater.updateAddAmount(response.stockId(),stock.amount());
        return vendingMachineId;
    }
}
