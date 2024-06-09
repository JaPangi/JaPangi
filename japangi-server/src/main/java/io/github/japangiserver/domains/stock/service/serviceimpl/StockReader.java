package io.github.japangiserver.domains.stock.service.serviceimpl;

import io.github.japangiserver.domains.stock.AddStock;
import io.github.japangiserver.domains.stock.persistence.repository.StockRepository;
import io.github.japangiserver.presentation.stock.dto.response.StockStatusResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StockReader {

    private final StockRepository stockRepository;

/*    @Transactional(readOnly = true)
    public StockEntity getStockFromDrinkAndVendingMachine(Long drinkId, Long vendingMachineId) {
        return stockRepository.findByDrinkIdAndVendingMachineId(drinkId, vendingMachineId)
            .orElseThrow(() -> new IllegalStateException("재고가 존재하지 않습니다!"));
    }*/

    @Transactional(readOnly = true)
    public AddStock getStock(Long drinkId, Long vendingMachineId) {
       return stockRepository.findByDrinkIdAndVendingMachineId(drinkId,vendingMachineId)
           .map(stock -> new AddStock(drinkId,stock.getAmount(),vendingMachineId))
           .orElseThrow(() -> new IllegalStateException("재고가 존재하지 않습니다!"));
    }

 /*   @Transactional(readOnly = true)
    public StockEntity getStockEntity(Long drinkId, Long vendingMachineId){
        return stockRepository.findByDrinkIdAndVendingMachineId(drinkId,vendingMachineId)
            .orElseThrow(() -> new IllegalStateException("재고가 존재하지 않습니다!"));
    }
*/
    @Transactional(readOnly = true)
    public List<StockStatusResponse> getCurrentStockStatus(Long vendingMachineId){
        return stockRepository.findByVendingMachineId(vendingMachineId)
            .stream()
            .map(StockStatusResponse::fromEntity)
            .toList();
    }
}
