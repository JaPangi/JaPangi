package io.github.japangiserver.domains.stock.persistence;

import io.github.japangiserver.domains.stock.AddStock;
import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import io.github.japangiserver.domains.stock.persistence.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockEntityReader {
    private final StockRepository stockRepository;

    public StockEntity getStockEntity(AddStock stock) {
        return stockRepository.findByDrinkIdAndVendingMachineId(
                stock.drinkId(), stock.vendingMachineId()
            )
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 재고입니다!"));
    }
}
