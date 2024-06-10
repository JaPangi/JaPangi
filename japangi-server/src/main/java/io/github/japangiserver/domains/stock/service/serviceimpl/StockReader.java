package io.github.japangiserver.domains.stock.service.serviceimpl;

import io.github.japangiserver.domains.stock.AddStock;
import io.github.japangiserver.domains.stock.persistence.repository.StockRepository;
import io.github.japangiserver.presentation.stock.dto.response.StockStatusResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 재고 조회 implement Layer
 */
@Component
@RequiredArgsConstructor
public class StockReader {

    private final StockRepository stockRepository;

    /** NOTE
     * 재고 domain 조회 구현체
     * @param drinkId 음료 Id(PK)
     * @param vendingMachineId 자판기 Id(PK)
     */
    @Transactional(readOnly = true)
    public AddStock getStock(Long drinkId, Long vendingMachineId) {
       return stockRepository.findByDrinkIdAndVendingMachineId(drinkId,vendingMachineId)
           .map(stock -> new AddStock(drinkId,stock.getAmount(),vendingMachineId))
           .orElseThrow(() -> new IllegalStateException("재고가 존재하지 않습니다!"));
    }

    /** NOTE
     * 현재 재고 조회 구현체
     * @param vendingMachineId 자판기 Id(PK)
     */
    @Transactional(readOnly = true)
    public List<StockStatusResponse> getCurrentStockStatus(Long vendingMachineId){
        return stockRepository.findByVendingMachineId(vendingMachineId)
            .stream()
            .map(StockStatusResponse::fromEntity)
            .toList();
    }
}
