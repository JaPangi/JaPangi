package io.github.japangiserver.domains.stock.application.serviceimpl;

import io.github.japangiserver.domains.stock.adapter.persistence.entity.StockEntity;
import io.github.japangiserver.domains.stock.adapter.persistence.repository.StockRepository;
import io.github.japangiserver.domains.stock.application.dto.response.StockResponse;
import io.github.japangiserver.domains.stock.application.dto.response.StockStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockReader {

    private final StockRepository stockRepository;

    public StockEntity getStockFromDrinkAndVendingMachine(Long drinkId, Long vendingMachineId) {
        return stockRepository.findByDrinkIdAndVendingMachineId(drinkId, vendingMachineId)
            .orElseThrow(() -> new IllegalStateException("재고가 존재하지 않습니다!"));
    }

    public StockResponse getStockResponse(Long drinkId, Long vendingMachineId) {
        StockEntity stockEntity = getStockFromDrinkAndVendingMachine(drinkId, vendingMachineId);
        return StockResponse.builder()
            .stockId(stockEntity.getStockId())
            .amount(stockEntity.getAmount())
            .build();
    }

    public StockEntity findStock(Long stockId) {
        return stockRepository.findById(stockId)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 재고입니다!"));
    }

    public List<StockStatus> getCurrentStockStatus(Long vendingMachineId){
        return stockRepository.findByVendingMachineId(vendingMachineId)
            .stream()
            .map(StockStatus::fromEntity)
            .toList();
    }
}
